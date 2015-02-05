package br.com.ttrans.samapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ttrans.samapp.annotation.UniqueSiteType;
import br.com.ttrans.samapp.service.SiteTypeService;

public class UniqueSiteTypeValidator implements ConstraintValidator<UniqueSiteType, String> {
	
	@Autowired
	private SiteTypeService service;
	
	@Override
	public void initialize(UniqueSiteType constraintAnnotation){
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context){
		
		return (service.findByName(username) == null);
	}

}
