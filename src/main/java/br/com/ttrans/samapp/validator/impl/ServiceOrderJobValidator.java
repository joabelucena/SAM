package br.com.ttrans.samapp.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.validator.Validator;

public class ServiceOrderJobValidator extends Validator {

	@Autowired
	private DAO dao;
	
	@Override
	protected void validAdd(Object obj, Errors e) {
		// TODO Auto-generated method stub
		super.validAdd(obj, e);
	}

	@Override
	protected void validEdit(Object obj, Errors e) {
		// TODO Auto-generated method stub
		super.validEdit(obj, e);
	}

	@Override
	protected void validDelete(Object obj, Errors e) {
		// TODO Auto-generated method stub
		super.validDelete(obj, e);
	}
}
