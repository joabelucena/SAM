package br.com.ttrans.samapp.validator;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@Component
public class ErrorMessageHandler {
	
	@Autowired
	MessageSource messageSource;
	
	public String toStringList(Errors e, Locale locale){
		
		List<ObjectError> errors = e.getAllErrors();
		
		String result = "<br><br>";
		
		for(int i = 0; i<errors.size();i++){
			result += "- " + messageSource.getMessage(errors.get(i).getCode(), null, locale) + "<br>";
		}
		
		return result;
	}
}
