package br.com.ttrans.samapp.validator.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.model.User;
import br.com.ttrans.samapp.validator.Validator;

@Component
public class ServiceOrderValidator extends Validator {
	
	@Autowired
	private DAO dao;

	@Override
	protected void validAdd(Object obj, User user, Errors e){
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
	protected void validEdit(Object obj, User user, Errors e) {
		super.validEdit(obj, user, e);
		
		ServiceOrder so = (ServiceOrder) obj;
		
		/**
		 * Validation for status changing
		 */
		if(so.statusChanged()){
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("role"		, user.getRole()					);
			map.put("curstatus"	, so.getLastLog().getCurstatus()	);
			map.put("nxtstatus"	, so.getStatus()					);
			
			if(!dao.existCpo(StatusRule.class, map)){
				e.reject("response.so.StatusNotAllowed");
			}
		}
	}

	@Override
	protected void validDelete(Object obj, User user, Errors e) {
		// TODO Auto-generated method stub
		super.validDelete(obj, user, e);
	}
}
