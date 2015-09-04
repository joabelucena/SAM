package br.com.ttrans.samapp.snmp;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;

@Component
public class SnmpServer implements CommandResponder {

	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;
	private int n = 0;
	private long start = -1;

	@Autowired
	private DAO dao;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private EventService eventService;
	
	private static final Logger logger = LoggerFactory.getLogger(SnmpServer.class);
	
	private static final String USR_SNMP = "SAM_SNMP";

	public SnmpServer() {
	}


	/** Starts the server **/
	public void run() {
		try {
			
			logger.info("Initializing Snmp Server...");			
			init();
			snmp.addCommandResponder(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** Stops the server **/
	public void close() {
		try {
			System.out.println("## Closing Snmp Server...");
			snmp.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init() throws UnknownHostException, IOException {
		threadPool = ThreadPool.create("Trap", 10);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,
				new MessageDispatcherImpl());
		listenAddress = GenericAddress.parse(System.getProperty(
				"snmp4j.listenAddress",
				"udp:" + dao.getMv("SYS_IPSNMP", "") + "/"
						+ dao.getMv("SYS_PORTSNMP", "")));

		TransportMapping transport;
		if (listenAddress instanceof UdpAddress) {
			transport = new DefaultUdpTransportMapping(
					(UdpAddress) listenAddress);
		} else {
			transport = new DefaultTcpTransportMapping(
					(TcpAddress) listenAddress);
		}
		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
				MPv3.createLocalEngineID()), 0);
		usm.setEngineDiscoveryEnabled(true);

		snmp = new Snmp(dispatcher, transport);
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3(usm));
		SecurityModels.getInstance().addSecurityModel(usm);
		snmp.getUSM().addUser(
				new OctetString("MD5DES"),
				new UsmUser(new OctetString("MD5DES"), AuthMD5.ID,
						new OctetString("UserName"), PrivDES.ID,
						new OctetString("PasswordUser")));
		snmp.getUSM().addUser(new OctetString("MD5DES"),
				new UsmUser(new OctetString("MD5DES"), null, null, null, null));

		snmp.listen();

	}

	public void processPdu(CommandResponderEvent event) {

		if (start < 0) {
			start = System.currentTimeMillis() - 1;
		}

		n++;

		if ((n % 100 == 1)) {
			System.out.println("Processed "
					+ (n / (double) (System.currentTimeMillis() - start))
					* 1000 + "/s, total=" + n);
		}

		StringBuffer msg = new StringBuffer();
		msg.append(event.toString());

		String oid = "";

		if (event != null && event.getPDU() != null) {
			
			@SuppressWarnings("unchecked")
			Vector<? extends VariableBinding> recVBs = event.getPDU()
					.getVariableBindings();

			// Parsing Trap IP
			String PeerAddress = event.getPeerAddress().toString();
			String[] IpParts = PeerAddress.split("/");
			String Ip = IpParts[0];

			Event eventdb = new Event();
			Equipment equipment = equipmentService.get(Ip);

			String eve_site = equipment.getSite().getDesc();
			String eve_model = equipment.getModel().getDesc();

			// Event Datetime
			Date eve_datetime = new Date();

			oid = equipmentService.getOidByIp(Ip);
			
			System.out.println(oid);

			boolean lAchou = !oid.equals(null);

			// Grava dados do trap
			if (lAchou) {

				for (int i = 0; i < recVBs.size(); i++) {
					VariableBinding recVB = recVBs.elementAt(i);

					if (oid.equals(recVB.getOid().toString())
							&& !recVB.getVariable().equals(null)) {

						eventdb.setEquipment(new Equipment(Ip));
						eventdb.setAlarm(new Alarm(recVB.getVariable()
								.toString()));
						eventdb.setDatetime(eve_datetime);
						eventdb.setSite(eve_site);
						eventdb.setModel(eve_model);
						eventdb.setInsert(USR_SNMP);

						eventService.add(eventdb);

					}

				}

				// Equipamento não encontrado, grava alarme do SAM
			} else {

				eventdb.setEquipment(new Equipment(Ip));
				eventdb.setAlarm(new Alarm("NI"));// Equipamento não cadastrado
				eventdb.setDatetime(eve_datetime);
				eventdb.setSite(eve_site);
				eventdb.setModel(eve_model);
				eventdb.setInsert(USR_SNMP);

				eventService.add(eventdb);
			}

		}

	}
}