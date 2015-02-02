package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Site;

public interface SiteService {
	public void add(Site site);
	public void edit(Site site);
	public void delete(int sitId);
	public Site getSite(int sitId);
	public Site findByName(String styDesc);
	public List getAllSite();
	public List loadData();
}
