package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Site;

@SuppressWarnings("rawtypes")
public interface SiteDao {
	public void add(Site site, Authentication authentication);
	public void edit(Site site, Authentication authentication);
	public void delete(Site site, Authentication authentication);
	public Site get(int id);
	public Site findByName(String styDesc);
	public List getAll();
	public List loadData();
}
