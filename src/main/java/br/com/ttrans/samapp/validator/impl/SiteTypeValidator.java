package br.com.ttrans.samapp.validator.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Site;
import br.com.ttrans.samapp.model.SiteType;
import br.com.ttrans.samapp.service.SiteTypeService;
import br.com.ttrans.samapp.validator.Validator;

@Component
public class SiteTypeValidator extends Validator {

	@Autowired
	private SiteTypeService typeService;

	@Autowired
	private DAO dao;

	@Override
	protected void validAdd(Object obj, Errors e) {
		SiteType type = (SiteType) obj;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("sty_description", type.getDesc());
		
		if (dao.ExistCPO(SiteType.class, map)) {
			e.rejectValue("sty_description",
					"error.sitetype.description.notunique");
		}
		
	}

	@Override
	protected void validEdit(Object obj, Errors e) {
		// Code Here Edit Validations
	}

	@Override
	protected void validDelete(Object obj, Errors e) {
		SiteType type = (SiteType) obj;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("type", type);
		
		if (dao.ExistCPO(Site.class, map)) {
			e.rejectValue("sty_description",
					"error.sitetype.description.used");
		}
		
	}

}
