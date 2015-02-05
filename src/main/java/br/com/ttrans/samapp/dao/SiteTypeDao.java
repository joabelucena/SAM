package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.SiteType;

public interface SiteTypeDao {
	public void add(SiteType siteType);
	public void edit(SiteType siteType);
	public void delete(int styId);
	public SiteType getSiteType(int styId);
	public SiteType findByName(String styDesc);
	public List getAllSiteType();
	public List loadData();
}
