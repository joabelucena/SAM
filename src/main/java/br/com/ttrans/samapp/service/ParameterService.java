package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Parameters;

public interface ParameterService {
	public void add(Parameters parameter, Authentication authentication);
	public void edit(Parameters parameter, Authentication authentication);
	public void delete(Parameters parameter, Authentication authentication);
	public Parameters get(int id);
	public List<Parameters> getAll();
}
