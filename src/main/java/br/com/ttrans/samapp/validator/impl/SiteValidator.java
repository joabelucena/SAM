package br.com.ttrans.samapp.validator.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Site;
import br.com.ttrans.samapp.model.SiteType;
import br.com.ttrans.samapp.service.SiteService;
import br.com.ttrans.samapp.validator.Validator;

@Component
public class SiteValidator extends Validator {

	@Autowired
	private SiteService service;

	@Autowired
	private DAO dao;

	@Override
	protected void validAdd(Object obj, Errors e) {
		
		Site site = (Site) obj;
		Map<String, Object> map = new HashMap<String, Object>();
		
		/* Type Validation
		 *
		 */
		if (!e.hasErrors()){
			if (site.getType() == null) {
				e.rejectValue("type.sty_description", "error.site.type.notnull");
			}
			
			if (site.getDesc().isEmpty()) {
				e.rejectValue("sit_description", "error.sites.description.notnull");
			}
			
			if (site.getShortname().isEmpty()) {
				e.rejectValue("sit_shortname", "error.sites.shortname.notnull");
			}
		}
		
		/* Unique Constraint
		 * 
		 */
		map.clear();
		
		map.put("sit_description"	,site.getDesc());
		map.put("sit_shortname"		,site.getShortname());
		map.put("type"				,site.getType());
		map.put("parent"			,site.getParent());
		
		// Unique Constraint
		if (!e.hasErrors() && dao.ExistCPO(Site.class,map)) {
			e.rejectValue("sit_description","error.site.description.notunique");
		}
		
	}
	
	@Override
	protected void validEdit(Object obj, Errors e) {
		Site site = (Site) obj;
		Map<String, Object> map = new HashMap<String, Object>();
		
		// Type Validation
		map.clear();
		map.put("sit_description", site.getType().getDesc());
		if (site.getType().getDesc().isEmpty()) {
			e.rejectValue("type.sty_description", "error.site.type.notnull");
		} else {

			// Type Validation
			if (!dao.ExistCPO(SiteType.class,map)) {
				e.rejectValue("type.sty_description",
						"error.site.type.notfound");
			}
		}
		
	}

	@Override
	protected void validDelete(Object obj, Errors e) {
		// TODO Auto-generated method stub
		super.validDelete(obj, e);
	}

}