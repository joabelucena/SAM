package br.com.ttrans.samapp.snmp;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.PDUv1;
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
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.library.IP;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.EquipmentOID;
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
		super();
	}
	
	/** Starts the server **/
	public void run() {
		try {
			
			logger.info("Initializing SNMP Server...");
			init();
			snmp.addCommandResponder(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** Stops the server **/
	public void close() {
		try {
			logger.info("Closing SNMP Server...");
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
		
		/**
		 * Cast to v1 version to access 'enterprise' and 'specificTrap' attributes
		 */
		PDUv1 pdu = (PDUv1) event.getPDU();
		
		if (event != null && pdu != null) {
			
			String oid = pdu.getEnterprise().toString()
					.concat(".")
					.concat(String.valueOf(pdu.getSpecificTrap()));
			
			// Parsing Trap IP
			IP ip =  new IP(event.getPeerAddress().toString().split("/")[0]);			
			
			Equipment equipment = equipmentService.get(ip);
			
			logger.debug("NEW TRAP >> IP: "+ ip + " | OID: " + oid);
			
			//Found equipment
			if (equipment != null) {
				
				List<EquipmentOID> oids = new ArrayList<EquipmentOID>(equipment.getModel().getOIDs());
				
				/**
				 * 
				 */
				switch(pdu.getGenericTrap()){
					case 0:
						/**
						 * coldStart(0) - Indicates that the agent has rebooted. All management variables will be reset; specifically, Counters and Gauges will be reset to zero (0).
						 * One nice thing about thecoldStart trap is that it can be used to determine when new hardware is added to the network. When a device is powered on, it sends 
						 * this trap to its trap destination. If the trap destination is set correctly (i.e., to the IP address of your NMS) the NMS can receive the trap and determine
						 * whether it needs to manage the device.
						 */
						//TODO Implement treatment for this type of trap.				
						break;
					case 1:
						/**
						 * warmStart(1) - Indicates that the agent has reinitialized itself. None of the management variables will be reset. 
						 */
						//TODO Implement treatment for this type of trap.
						break;
					case 2:
						/**
						 * linkDown(2) - Sent when an interface on a device goes down. The first variable binding identifies which interface went down. 
						 */
						//TODO Implement treatment for this type of trap.				
						break;
					case 3:
						/**
						 * linkUp(3) - Sent when an interface on a device comes back up. The first variable binding identifies which interface came back up.						
						 */
						break;
					case 4:
						/**
						 * authenticationFailure(4) - Indicates that someone has tried to query your agent with an incorrect community string; useful in
						 * determining if someone is trying to gain unauthorized access to one of your devices.
						 */
						//TODO Implement treatment for this type of trap.			
						break;
					case 5:
						/**
						 * egpNeighborLoss(5) - Indicates that an Exterior Gateway Protocol (EGP) neighbor has gone down.						
						 */
						//TODO Implement treatment for this type of trap.
						break;
					case 6:
						/**
						 * enterpriseSpecific(6) - Indicates that the trap is enterprise-specific. SNMP vendors and users define their own traps under the
						 * private-enterprise branch of the SMI object tree. To process this trap properly, the NMS has to decode the specific trap number
						 * that is part of the SNMP message. 
						 */
						
						/**
						 * Iterates over equipment's nested OIDs for finding any match to the trap
						 */
						for(EquipmentOID o : oids){
														
							if(o.getOID().equals(oid)){
								
								Alarm alarm = new Alarm(o.getAlarm());
																
								Event e = new Event(equipment
													, alarm
													, new Date()
													, USR_SNMP);
								try {
									eventService.add(e);
								} catch (Exception e2) {
									logger.error("Erro: " + e2.getMessage());
								}
							}
						}
						break;
					
					default:
						/**
						 * Default block
						 */
						break;

				}
				
			} else {
				//Equipment not found
				logger.debug("Não foi localizado o equipamento com o IP: " + ip);
				logger.debug("OID: " + oid);
				
			}
		}
	}
}