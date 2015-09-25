package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.Counter.*;
import br.com.ttrans.samapp.model.Equipment;

public interface CounterDao {
	public void countIt(Alarm alarm, Equipment equipment);
	public void reset(Counter ct);
	public void reset(Alarm alarm, Equipment equipment);
	public Counter get(CounterId id);
	public Integer getCountByType(Equipment equipment, AlarmType type);
	public List<Counter> loadData();
}
