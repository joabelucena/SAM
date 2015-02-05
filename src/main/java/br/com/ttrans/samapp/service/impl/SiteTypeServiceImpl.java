package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(SiteType siteType) {
		siteTypeDao.add(siteType);
	}

	@Transactional
	public void edit(SiteType siteType) {
		siteTypeDao.edit(siteType);

	}

	@Transactional
	public void delete(int styId) {
		siteTypeDao.delete(styId);
	}

	@Transactional
	public SiteType getSiteType(int styId) {
		return siteTypeDao.getSiteType(styId);
	}
	
	@Transactional
	public SiteType findByName(String styDesc){
		return siteTypeDao.findByName(styDesc);
	}

	@Transactional
	public List getAllSiteType() {
		return siteTypeDao.getAllSiteType();
	}
	
	@Transactional
	public List loadData() {
		return siteTypeDao.loadData();
	}

}
