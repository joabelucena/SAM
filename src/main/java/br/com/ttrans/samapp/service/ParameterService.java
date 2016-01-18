package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Parameter;

public interface ParameterService {
	public void add(Parameter parameter, Authentication authentication);
	public void edit(Parameter parameter, Authentication authentication);
	public void delete(Parameter parameter, Authentication authentication);
	public List<Parameter> loadData();
}
