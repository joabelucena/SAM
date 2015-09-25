package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.SeverityLevel;

public interface SeverityLevelService {
	public void add(SeverityLevel severity, Authentication authentication);
	public void edit(SeverityLevel severity, Authentication authentication);
	public void delete(SeverityLevel severity, Authentication authentication);
	public List<SeverityLevel> loadData();
}
