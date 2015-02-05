package br.com.ttrans.samapp.validator;

import org.springframework.validation.Errors;

public abstract class Validator {

	public void validate(Object obj, Errors e, String action){
		switch(action.toLowerCase()){
		
		case "add":
			this.validAdd(obj, e);
			break;
		case "edit":
			this.validEdit(obj, e);
			break;
		case "delete":
			this.validDelete(obj, e);
			break;
		default:
			break;
		}
	};
	
	protected void validAdd(Object obj, Errors e){};
	protected void validEdit(Object obj, Errors e){};
	protected void validDelete(Object obj, Errors e){};
}
