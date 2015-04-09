package br.com.ttrans.samapp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.model.Event;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(Event event) {
		session.getCurrentSession().save(event);

	}

	@Override
	public void edit(Event event, Authentication authentication) {
		event.setUsr_update(authentication.getName());
		session.getCurrentSession().update(event);

	}

	@Override
	public void delete(Event event, Authentication authentication) {
		event.setUsr_update(authentication.getName());
		event.setDeleted("*");				
		session.getCurrentSession().update(event);
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
	
	
	@Override
	public Event get(long id) {
		return (Event) session.getCurrentSession().get(Event.class, id);
	}

	@Override
	public List getAll() {
		return session.getCurrentSession()
				.createQuery("from Events where deleted <> '*'").list();
	}

	@Override
	public List loadData() {

		String cQuery = "";

		SQLQuery qQuery;

		cQuery = "SELECT";
		cQuery += "	THIS.EVE_ID				AS ID,";
		cQuery += "	CASE";
		cQuery += "		WHEN THIS.EVE_RECO_USER IS NULL"
				+ "		THEN 'false'";
		cQuery += "		ELSE 'true'"
				+ "		END					AS RECO_USER,";
		cQuery += "	B.SLE_DESCRIPTION		AS SEVERITY,";
		cQuery += "	B.SLE_ID				AS SEVERITY_ID,";
		cQuery += "	THIS.EVE_EQUIPMENT_ID	AS EQUIP_ID,";
		cQuery += "	D.EMO_DESCRIPTION		AS QUIP_MODEL,";
		cQuery += "	THIS.EVE_ALARM_ID		AS ALARM_ID,";
		cQuery += "	A.ALM_DESCRIPTION		AS ALARM_DESC,";
		cQuery += "	E.SIT_DESCRIPTION		AS SITE_DESC,";
		cQuery += "	F.SSY_ID				AS SYS,";
		cQuery += "	F.SSY_DESCRIPTION		AS SYS_DESC,";
		cQuery += "	THIS.EVE_DATETIME		AS DATETIME";
		cQuery += " FROM";
		cQuery += "		EVENTS THIS";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " ALARMS A";
		cQuery += "    ON THIS.EVE_ALARM_ID=A.ALM_ID";
		cQuery += "    AND A.DELETED <> '*'";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SEVERITY_LEVEL B";
		cQuery += "    ON A.ALM_SEVERITY_ID=B.SLE_ID";
		cQuery += "    AND A.DELETED <> '*'";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " EQUIPMENTS C";
		cQuery += "    ON THIS.EVE_EQUIPMENT_ID=C.EQU_ID";
		cQuery += "    AND C.DELETED <> '*'";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " EQUIPMENTS_MODEL D";
		cQuery += "     ON C.EQU_MODEL_ID=D.EMO_ID";
		cQuery += "     AND D.DELETED <> '*'";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SITES E";
		cQuery += "     ON C.EQU_SITE_ID=E.SIT_ID";
		cQuery += "     AND E.DELETED <> '*'";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " SUB_SYSTEM F";
		cQuery += "     ON C.EQU_SYSTEM_ID=F.SSY_ID";
		cQuery += "     AND F.DELETED <> '*'";
		cQuery += " LEFT OUTER JOIN";
		cQuery += " OPERATIONAL_STATE G";
		cQuery += "     ON THIS.EVE_OPER_STATE_ID=G.OST_ID";
		cQuery += "     AND G.DELETED <> '*'";
		cQuery += " WHERE";
		cQuery += " THIS.DELETED <> '*'";
		cQuery += " AND THIS.EVE_SOLV_USER IS NULL";

		qQuery = session.getCurrentSession().createSQLQuery(cQuery);
		
		qQuery.addScalar("ID"			, Hibernate.STRING);
		qQuery.addScalar("RECO_USER"	, Hibernate.BOOLEAN);
		qQuery.addScalar("SEVERITY"		, Hibernate.STRING);
		qQuery.addScalar("SEVERITY_ID"	, Hibernate.STRING);
		qQuery.addScalar("EQUIP_ID"		, Hibernate.STRING);
		qQuery.addScalar("QUIP_MODEL"	, Hibernate.STRING);
		qQuery.addScalar("ALARM_ID"		, Hibernate.STRING);
		qQuery.addScalar("ALARM_DESC"	, Hibernate.STRING);
		qQuery.addScalar("SITE_DESC"	, Hibernate.STRING);
		qQuery.addScalar("SYS"			, Hibernate.STRING);
		qQuery.addScalar("SYS_DESC"		, Hibernate.STRING);	
		qQuery.addScalar("DATETIME"		, Hibernate.STRING);
		
		return qQuery.list();

	}
}