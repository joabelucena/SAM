package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.Counter.CounterId;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.service.CounterService;

@Repository
public class CounterServiceImpl implements CounterService {
	
	@Autowired
	private CounterDao dao;
	
	@Transactional
	public void countIt(Alarm alarm, Equipment equipment){
		dao.countIt(alarm, equipment);		
	}
	
	@Transactional
	public void reset(Counter ct){
		dao.reset(ct);
	}
	
	@Transactional
	public void reset(Alarm alarm, Equipment equipment){
		dao.reset(alarm,equipment);
	}
	
	@Transactional
	public Counter get(CounterId id){
		
		return dao.get(id);
	}
	
	@Transactional
	public List<Counter> loadData() {
		return dao.loadData();
	}

}
