package br.com.ttrans.samapp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.Counter.CounterId;
import br.com.ttrans.samapp.model.Equipment;

@Repository
public class CounterDaoImpl implements CounterDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void countIt(Alarm alarm, Equipment equipment) {

		if (alarm instanceof Alarm && equipment instanceof Equipment) {
			/*
			 * Os alarmes não cadastrados não serão incrementados.
			 */
			// Incrementa alarme
			if (alarm.getCounterInc().equals("Y")) {
				String cQuery = null;

				cQuery = "UPDATE Counter " + "SET ACO_COUNTER = ACO_COUNTER+1 "
						+ "WHERE ACO_ALARM_ID = :ALARM "
						+ "AND ACO_EQUIPMENT_ID = :EQUIPMENT";

				Query qQuery = session.getCurrentSession().createQuery(cQuery);
				qQuery.setParameter("ALARM", alarm.getId());
				qQuery.setParameter("EQUIPMENT", equipment.getId());

				try {

					if (qQuery.executeUpdate() == 0) {

						try {

							Counter ct = new Counter();
							

							ct.setId(new CounterId(alarm, equipment));

							// Caso nao tenha encontrado registro pra atualizar,
							// insere novo registro na base
							session.getCurrentSession().save(ct);

						} catch (Exception i) {

						}

					}
				} catch (Exception e) {
					// TODO tratar excecao
				}
			}
		}
	}
	
	@Override
	public void reset(Counter ct){
		ct.setResetDate(new Date());
		ct.setCounter(0);
		session.getCurrentSession().update(ct);
	}
	
	@Override
	public void reset(Alarm alarm, Equipment equipment){
		
		Counter ct = (Counter) session.getCurrentSession().get(Counter.class, new CounterId(alarm, equipment));
		
		if(ct instanceof Counter){
			ct.setResetDate(new Date());
			ct.setCounter(0);
			session.getCurrentSession().update(ct);
		}
	}
	
	@Override
	public Counter get(CounterId id){
		return (Counter) session.getCurrentSession().get(Counter.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Counter> loadData() {

		return session.getCurrentSession().createCriteria(Counter.class).list();
		
	}
}
