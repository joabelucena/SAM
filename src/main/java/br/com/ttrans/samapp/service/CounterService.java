package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Counter.*;

public interface CounterService {
	public void countIt(Alarm alarm, Equipment equipment);
	public void reset(Counter ct);
	public void reset(Alarm alarm, Equipment equipment);
	public Counter get(CounterId id);
	public List<Counter> loadData();
}
