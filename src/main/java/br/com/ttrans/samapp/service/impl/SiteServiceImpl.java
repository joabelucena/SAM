package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(Site site, Authentication authentication) {
		siteDao.add(site, authentication);
	}

	@Transactional
	public void edit(Site site, Authentication authentication) {
		siteDao.edit(site, authentication);
	}

	@Transactional
	public void delete(Site site, Authentication authentication) {
		siteDao.delete(site, authentication);
	}

	@Transactional
	public Site get(int id) {
		return siteDao.get(id);
	}

	@Transactional
	public List getAll() {
		return siteDao.getAll();
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
