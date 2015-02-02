package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Parameters;

public interface ParameterService {
	public void add(Parameters parameter);
	public void edit(Parameters parameter);
	public void delete(int parId);
	public Parameters getParameter(int parId);
	public List getAllParameter();
}
