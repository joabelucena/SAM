package br.com.ttrans.samapp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmFilter;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;

@Repository
public class EventDaoImpl implements EventDao {
	
	private static final Logger logger = LoggerFactory.getLogger(EventDaoImpl.class);

	@Autowired
	private SessionFactory session;

	@Override
	public void add(Event event) {
		session.getCurrentSession().save(event);

	}

	@Override
	public void edit(Event event, Authentication authentication) {
		event.setUpdate(authentication.getName());
		session.getCurrentSession().update(event);
		
	}

	@Override
	public void delete(Event event, Authentication authentication) {
		session.getCurrentSession().delete(event);
	}
	
	@Override
	public int recognize(Long[] ids, Authentication authentication){
		
		String cQuery = null;
		
		cQuery = "UPDATE Event "
				+ "SET EVE_RECO_USER = :USER, "
				+ "EVE_RECO_DATE = :DATE "
				+ "WHERE EVE_ID IN (:IDS) "
				+ "AND EVE_RECO_USER IS NULL";

		Query qQuery = session.getCurrentSession().createQuery(cQuery);
		qQuery.setParameter("USER", authentication.getName());
		qQuery.setParameter("DATE", new Date());
		qQuery.setParameterList("IDS", ids );

		return qQuery.executeUpdate();
	}
	
	@Override
	public int normalize(Long id, Authentication authentication){
		
		String cQuery = null;
		
		cQuery = "UPDATE Event "
				+ "SET EVE_SOLV_USER = :USER, "
				+ "EVE_SOLV_DATE = :DATE "
				+ "WHERE EVE_ID = :ID "
				+ "AND EVE_RECO_USER IS NOT NULL";
		
		Query qQuery = session.getCurrentSession().createQuery(cQuery);
		qQuery.setParameter("USER", authentication.getName());
		qQuery.setParameter("DATE", new Date());
		qQuery.setParameter("ID", id );

		return qQuery.executeUpdate();
	}
	
	/** Normalizacao automatica **/
	@Override
	public void normalize(List<String> alarmsId, String equipment, String user){
		
		String cQuery = null;
		
		cQuery = "UPDATE Event "
				+ "SET EVE_SOLV_USER = :USER, "
				+ "EVE_SOLV_DATE = :DATE "
				+ "WHERE EVE_ALARM_ID IN (:IDS) "
				+ "AND EVE_EQUIPMENT_ID = :EQUIP";
		
		Query qQuery = session.getCurrentSession().createQuery(cQuery);
		qQuery.setParameter("USER"	, user);
		qQuery.setParameter("DATE"	, new Date());
		qQuery.setParameterList("IDS"	, alarmsId);
		qQuery.setParameter("EQUIP"	, equipment);
		
		qQuery.executeUpdate();
	}
	
	@Override
	public int countByAlarm(Equipment equipment, Alarm alarm, Date date){
		
		Criteria crit = session.getCurrentSession().createCriteria(Event.class);
		
		crit.add(Restrictions.ge("datetime"	, date));
		crit.add(Restrictions.eq("alarm"	, alarm));
		crit.add(Restrictions.eq("equipment", equipment));
		
		return (int) crit.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	
	@Override
	public int countByType(Equipment equipment, AlarmType type, Date date){
		
		Criteria crit = session.getCurrentSession().createCriteria(Event.class,"event")
				.createCriteria("alarm","alarm",CriteriaSpecification.LEFT_JOIN);
		
		crit.add(Restrictions.eq("alarm.type"		, type		));
		crit.add(Restrictions.ge("event.datetime"	, date		));
		crit.add(Restrictions.eq("event.equipment"	, equipment	));
		
		
		return (int) crit.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> activeAlarms(Equipment equipment, Alarm alarm){
		
		Criteria crit = session.getCurrentSession().createCriteria(Event.class);
		
		crit.add(Restrictions.eq("equipment", equipment));
		crit.add(Restrictions.eq("alarm"	, alarm));
		crit.add(Restrictions.eq("solvUser"	, ""));
		
		crit.setProjection(Projections.property("id"));
		
		return crit.list();
	}
	
	@Override
	public boolean isFiltered(Event event){
		
		try{
			Criteria crit = session.getCurrentSession().createCriteria(AlarmFilter.class);
			
			crit.add(Restrictions.eq("alarm"	, event.getAlarm()));
			crit.add(Restrictions.eq("equipment", event.getEquipment()));
	
			return (crit.list().size() > 0);
		}catch(Exception e){
			logger.error("Filtering Event error occurred. \n"
					+ "Alarm:"+event.getAlarm().getId()+"\n" 
					+ "Equipment:"+event.getEquipment().getId()+"\n"
					+ "Error details are showed bellow:");
			logger.error(e.getMessage());
			
		}
		
		return false;
	}
	
	@Override
	public Event get(long id) {
		return (Event) session.getCurrentSession().get(Event.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> loadData() {

		String cQuery = "";

		SQLQuery qQuery;

		cQuery = "SELECT";
		cQuery += "	THIS.EVE_ID								AS ID,";
		cQuery += "	CASE";
		cQuery += "		WHEN THIS.EVE_RECO_USER IS NULL"
				+ "		THEN 'false'";
		cQuery += "		ELSE 'true'"
				+ "		END									AS RECO,";
		cQuery += "	COALESCE(THIS.EVE_RECO_USER,'-')		AS RECO_USER,";
		cQuery += "	COALESCE(THIS.EVE_RECO_DATE,'-')		AS RECO_DATE,";
		cQuery += "	COALESCE(B.SLE_DESCRIPTION,'-')			AS SEVERITY,";
		cQuery += "	COALESCE(B.SLE_ID,'-')					AS SEVERITY_ID,";
		cQuery += "	THIS.EVE_EQUIPMENT_ID					AS EQUIP_ID,";
		cQuery += "	COALESCE(D.EMO_DESCRIPTION,'-')			AS EQUIP_MODEL,";
		cQuery += "	COALESCE(H.EMA_DESCRIPTION,'-')			AS EQUIP_MANUF,";
		cQuery += "	THIS.EVE_ALARM_ID						AS ALARM_ID,";
		cQuery += "	COALESCE(A.ALM_DESCRIPTION,'-')			AS ALARM_DESC,";
		cQuery += "	COALESCE(E.SIT_DESCRIPTION,'-')			AS SITE_DESC,";
		cQuery += "	COALESCE(F.SSY_ID,'-')					AS SYS,";
		cQuery += "	COALESCE(F.SSY_DESCRIPTION,'-')			AS SYS_DESC,";
		cQuery += "	COALESCE(I.SOR_ID,'-')					AS SO_ID,";
		cQuery += "	COALESCE(J.SOS_DESCRIPTION,'-')			AS SO_STATUS,";
		cQuery += "	THIS.EVE_DATETIME						AS DATETIME";
		cQuery += " FROM";
		cQuery += "		EVENTS THIS";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " ALARMS A";
		cQuery += "    ON THIS.EVE_ALARM_ID=A.ALM_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SEVERITY_LEVEL B";
		cQuery += "    ON A.ALM_SEVERITY_ID=B.SLE_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " EQUIPMENTS C";
		cQuery += "    ON THIS.EVE_EQUIPMENT_ID=C.EQU_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " EQUIPMENTS_MODEL D";
		cQuery += "     ON C.EQU_MODEL_ID=D.EMO_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SITES E";
		cQuery += "     ON C.EQU_SITE_ID=E.SIT_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SUB_SYSTEM F";
		cQuery += "     ON C.EQU_SYSTEM_ID=F.SSY_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " OPERATIONAL_STATE G";
		cQuery += "     ON THIS.EVE_OPER_STATE_ID=G.OST_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " EQUIPMENTS_MANUFACTURER H";
		cQuery += "     ON C.EQU_MANUFACTURER_ID=H.EMA_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SERVICE_ORDER I";
		cQuery += "     ON THIS.EVE_ID = I.SOR_EVENT_ID";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SERVICE_ORDER_STATUS J";
		cQuery += "     ON I.SOR_STATUS_ID = J.SOS_ID";
		
		
		
		cQuery += " WHERE";
		cQuery += " 	THIS.EVE_SOLV_USER IS NULL";

		qQuery = session.getCurrentSession().createSQLQuery(cQuery);
		
		qQuery.addScalar("ID"			, Hibernate.STRING);
		qQuery.addScalar("RECO"			, Hibernate.BOOLEAN);
		qQuery.addScalar("RECO_USER"	, Hibernate.STRING);
		qQuery.addScalar("RECO_DATE"	, Hibernate.STRING);
		qQuery.addScalar("SEVERITY"		, Hibernate.STRING);
		qQuery.addScalar("SEVERITY_ID"	, Hibernate.STRING);
		qQuery.addScalar("EQUIP_ID"		, Hibernate.STRING);
		qQuery.addScalar("EQUIP_MODEL"	, Hibernate.STRING);
		qQuery.addScalar("EQUIP_MANUF"	, Hibernate.STRING);
		qQuery.addScalar("ALARM_ID"		, Hibernate.STRING);
		qQuery.addScalar("ALARM_DESC"	, Hibernate.STRING);
		qQuery.addScalar("SITE_DESC"	, Hibernate.STRING);
		qQuery.addScalar("SYS"			, Hibernate.STRING);
		qQuery.addScalar("SYS_DESC"		, Hibernate.STRING);
		qQuery.addScalar("SO_ID"		, Hibernate.STRING);
		qQuery.addScalar("SO_STATUS"	, Hibernate.STRING);
		qQuery.addScalar("DATETIME"		, Hibernate.STRING);
		
		return qQuery.list();

	}
}