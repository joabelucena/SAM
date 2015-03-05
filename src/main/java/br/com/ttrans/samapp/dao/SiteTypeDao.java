package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.SiteType;

@SuppressWarnings("rawtypes")
public interface SiteTypeDao {
	public void add(SiteType siteType, Authentication authentication);
	public void edit(SiteType siteType, Authentication authentication);
	public void delete(SiteType siteType, Authentication authentication);
	public SiteType get(int id);
	public SiteType findByName(String styDesc);
	public List getAll();
	public List loadData();
}
