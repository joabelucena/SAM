package br.com.ttrans.samapp.dao.impl;

import java.util.LinkedList;
import java.util.List;

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
	public Event get(int id) {
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
		cQuery += "	TRIM(CAST(CASE";
		cQuery += "		WHEN THIS.EVE_RECO_USER IS NULL"
				+ "		THEN 'false'";
		cQuery += "		ELSE 'true'"
				+ "		END AS VARCHAR(5)))	AS RECO_USER,";
		cQuery += "	B.SLE_DESCRIPTION		AS SEVERITY,";
		cQuery += "	B.SLE_ID				AS SEVERITY_ID,";
		cQuery += "	B.SLE_DISPLAY_COLOR		AS SEVERITY_COLOR,";
		cQuery += "	THIS.EVE_EQUIPMENT_ID	AS EQUIP_ID,";
		cQuery += "	D.EMO_DESCRIPTION		AS QUIP_MODEL,";
		cQuery += "	THIS.EVE_ALARM_ID		AS ALARM_ID,";
		cQuery += "	A.ALM_DESCRIPTION		AS ALARM_DESC,";
		cQuery += "	E.SIT_DESCRIPTION		AS SITE_DESC,";
		cQuery += "	F.SSY_ID				AS SYS,";
		cQuery += "	F.SSY_DESCRIPTION		AS SYS_DESC,";
		cQuery += "	THIS.EVE_DATETIME		AS DATETIME";
		cQuery += " FROM";
		cQuery += "	EVENTS THIS";
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

		qQuery = session.getCurrentSession().createSQLQuery(cQuery);
		
		return qQuery.list();

	}

}
