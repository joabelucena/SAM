package br.com.ttrans.samapp.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.User;
import br.com.ttrans.samapp.validator.Validator;

@Component
public class StatusRuleValidator extends Validator {

	@Autowired
	private DAO dao;

	@Override
	protected void validAdd(Object obj, User user, Errors e) {
		// TODO Auto-generated method stub
		super.validAdd(obj, user, e);
	}

	@Override
	protected void validEdit(Object obj, User user, Errors e) {
		super.validEdit(obj, user, e);
		
	}

	@Override
	protected void validDelete(Object obj, User user, Errors e) {
		// TODO Auto-generated method stub
		super.validDelete(obj, user, e);
	}
}
