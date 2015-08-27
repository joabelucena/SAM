package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.SiteTypeDao;
import br.com.ttrans.samapp.model.SiteType;
import br.com.ttrans.samapp.service.SiteTypeService;

@Repository
public class SiteTypeServiceImpl implements SiteTypeService {

	@Autowired
	private SiteTypeDao siteTypeDao;
	
	@Transactional
	public void add(SiteType siteType, Authentication authentication) {
		siteTypeDao.add(siteType, authentication);
	}

	@Transactional
	public void edit(SiteType siteType, Authentication authentication) {
		siteTypeDao.edit(siteType, authentication);
	}

	@Transactional
	public void delete(SiteType siteType, Authentication authentication) {
		siteTypeDao.delete(siteType, authentication);
	}

	@Transactional
	public SiteType get(int id) {
		return siteTypeDao.get(id);
	}
	
	@Transactional
	public SiteType findByName(String styDesc){
		return siteTypeDao.findByName(styDesc);
	}

	@Transactional
	public List<SiteType> getAll() {
		return siteTypeDao.getAll();
	}
	
	@Transactional
	public List<SiteType> loadData() {
		return siteTypeDao.loadData();
	}
}
