package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Parameter;

public interface ParameterDao {
	public void add(Parameter parameter, Authentication authentication);
	public void edit(Parameter parameter, Authentication authentication);
	public void delete(Parameter parameter, Authentication authentication);
	public Parameter get(int id);
	public List<Parameter> getAll();
}
