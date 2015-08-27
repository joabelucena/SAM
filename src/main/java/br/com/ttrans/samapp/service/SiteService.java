package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Site;


public interface SiteService {
	public void add(Site site, Authentication authentication);
	public void edit(Site site, Authentication authentication);
	public void delete(Site site, Authentication authentication);
	public Site get(int id);
	public Site findByName(String styDesc);
	public List<Site> getAll();
	public List<Site> loadData();
	public List<String[]> trackIt(int id);
}
