package br.com.ttrans.samapp.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.ttrans.samapp.validator.UniqueSiteTypeValidator;

@Target({ FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueSiteTypeValidator.class})
@Deprecated
public @interface UniqueSiteType{
	
	String message() default "Not Unique";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	
}

