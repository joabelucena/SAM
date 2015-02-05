package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.SiteDao;
import br.com.ttrans.samapp.model.Site;
import br.com.ttrans.samapp.service.SiteService;

@Repository
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteDao siteDao;
	
	@Transactional
	public void add(Site site) {
		siteDao.add(site);
	}

	@Transactional
	public void edit(Site site) {
		siteDao.edit(site);

	}

	@Transactional
	public void delete(int sitId) {
		siteDao.delete(sitId);

	}

	@Transactional
	public Site getSite(int sitId) {
		return siteDao.getSite(sitId);
	}

	@Transactional
	public List getAllSite() {
		return siteDao.getAllSite();
	}
	
	@Transactional
	public List loadData() {
		return siteDao.loadData();
	}

	@Transactional
	public Site findByName(String styDesc) {
		return siteDao.findByName(styDesc);
	}

}
