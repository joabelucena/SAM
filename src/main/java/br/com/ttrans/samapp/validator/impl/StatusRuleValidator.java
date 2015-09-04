package br.com.ttrans.samapp.validator.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.validator.Validator;

@Component
public class StatusRuleValidator extends Validator {

	@Autowired
	private DAO dao;

	@Override
	protected void validAdd(Object obj, Errors e) {
		// TODO Auto-generated method stub
		super.validAdd(obj, e);
	}

	@Override
	protected void validEdit(Object obj, Errors e) {
		StatusRule rule = (StatusRule) obj;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("role"		, rule.getRole()		);
		map.put("curstatus"	, rule.getCurstatus()	);
		map.put("nxtstatus"	, rule.getNxtstatus()	);
		map.put("remark"	,rule.getRemark()		);
		
		if(!dao.existCpo(StatusRule.class, map)){
			e.reject("response.so.Deny");
		}
	}

	@Override
	protected void validDelete(Object obj, Errors e) {
		// TODO Auto-generated method stub
		super.validDelete(obj, e);
	}
}
