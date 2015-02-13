package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void edit(Event event) {
		session.getCurrentSession().update(event);

	}

	@Override
	public void delete(int eveId) {
		session.getCurrentSession().delete(getEvent(eveId));
	}

	@Override
	public Event getEvent(int eveId) {
		return (Event)session.getCurrentSession().get(Event.class, eveId);
	}

	@Override
	public List getAllEvent() {
		return session.getCurrentSession().createQuery("from Events where deleted <> '*'").list();
	}
	
	@Override
	public List loadData() {

		String cQuery = "";
		
		SQLQuery qQuery;
		
		
		cQuery = "SELECT";
		cQuery +="	THIS.EVE_ID				AS ID,";
		cQuery +="	B.SLE_DESCRIPTION		AS SEVERITY,";
		cQuery +="	B.SLE_ID				AS SEVERITY_ID,";
		cQuery +="	B.SLE_DISPLAY_COLOR		AS SEVERITY_COLOR,";
		cQuery +="	THIS.EVE_EQUIPMENT_ID	AS EQUIP_ID,";
		cQuery +="	D.EMO_DESCRIPTION		AS QUIP_MODEL,";
		cQuery +="	THIS.EVE_ALARM_ID		AS ALARM_ID,";
		cQuery +="	A.ALM_DESCRIPTION		AS ALARM_DESC,";
		cQuery +="	E.SIT_DESCRIPTION		AS SITE_DESC,";
		cQuery +="	F.SSY_ID				AS SYS,";
		cQuery +="	F.SSY_DESCRIPTION		AS SYS_DESC,";
		cQuery +="	THIS.EVE_DATETIME		AS DATETIME";
		cQuery +=" FROM";
		cQuery +="	EVENTS THIS";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" ALARMS A";             
		cQuery +="    ON THIS.EVE_ALARM_ID=A.ALM_ID";             
		cQuery +="    AND A.DELETED <> '*'";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" SEVERITY_LEVEL B";             
		cQuery +="    ON A.ALM_SEVERITY_ID=B.SLE_ID";             
		cQuery +="    AND A.DELETED <> '*'";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" EQUIPMENTS C";             
		cQuery +="    ON THIS.EVE_EQUIPMENT_ID=C.EQU_ID";             
		cQuery +="    AND C.DELETED <> '*'";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" EQUIPMENTS_MODEL D";             
		cQuery +="     ON C.EQU_MODEL_ID=D.EMO_ID";             
		cQuery +="     AND D.DELETED <> '*'";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" SITES E";             
		cQuery +="     ON C.EQU_SITE_ID=E.SIT_ID";             
		cQuery +="     AND E.DELETED <> '*'";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" SUB_SYSTEM F";             
		cQuery +="     ON C.EQU_SYSTEM_ID=F.SSY_ID";             
		cQuery +="     AND F.DELETED <> '*'";     
		cQuery +=" LEFT OUTER JOIN";
		cQuery +=" OPERATIONAL_STATE G";              
		cQuery +="     ON THIS.EVE_OPER_STATE_ID=G.OST_ID";
		cQuery +="     AND G.DELETED <> '*'";

		qQuery = session.getCurrentSession().createSQLQuery(cQuery);
		
		return qQuery.list();
		
	}

}
