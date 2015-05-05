package br.com.ttrans.samapp.validator.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.validator.Validator;

@Component
public class ServiceOrderValidator extends Validator {

	@Autowired
	private DAO dao;
	
	@Override
	protected void validAdd(Object obj, Errors e){
		ServiceOrder so = (ServiceOrder) obj;
		
		// Inicio nao pode ser depois do terminio
		if (so.getStartForecast().after(so.getEndForecast())) {
			e.reject("response.so.NonSequenceDate");
		}

		// Inicio nao pode ser antes de hoje
		if (so.getStartForecast().before(new Date())) {
			e.reject("response.so.PastDate");
		}
		
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
