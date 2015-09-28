package br.com.ttrans.samapp.validator;

import org.springframework.validation.Errors;

import br.com.ttrans.samapp.model.User;

public abstract class Validator {

	public void validate(Object obj, Errors e, User user, String action){
		switch(action.toLowerCase()){
		
		case "add":
			this.validAdd(obj, user, e);
			break;
		case "edit":
			this.validEdit(obj, user, e);
			break;
		case "delete":
			this.validDelete(obj, user, e);
			break;
		default:
			break;
		}
	};
	
	protected void validAdd(Object obj, User user, Errors e){};
	protected void validEdit(Object obj, User user, Errors e){};
	protected void validDelete(Object obj, User user, Errors e){};
}
