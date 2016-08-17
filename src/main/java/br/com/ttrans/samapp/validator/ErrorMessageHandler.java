package br.com.ttrans.samapp.validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.firebirdsql.jdbc.FBSQLException;
import org.hibernate.exception.DataException;
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
	
	public static void getUserMessage(Exception e, Map<String, Object> result) {
		
		String message ;
		
		if(e.getCause() instanceof FBSQLException){
			
			/*
			 * Firebird exception handling
			 */
			FBSQLException ex = (FBSQLException) e.getCause();
			
			switch (ex.getErrorCode()) {
			
			case 335544665:
				/*
				 * Unique key or primary key violation
				 */
				message = "Registro duplicado. Verifique as informações digitadas e tente novamente.";
				break;
				
			case 335544558:
				/*
				 * User constraint violation
				 */
				message = "Falha ao inserir registro. As informação solicitada não pode ser inserida devido a algumas regras do sistema.";
				break;
				
			case 335544466:
				/*
				 * Foreign key violation
				 */
				message = "Cadastre primeiro todas as informações necessárias vinculadas a esse cadastro e tente novamente.";
				break;

			default:
				message = "Problemas na requisição, favor entrar em contato com o administrador do sistema.";
				break;
			}
			
			
		} else if(e.getCause() instanceof DataException){
			
			/*
			 * Hibernate exception handling
			 */
			DataException ex = (DataException) e.getCause();
			
			switch (ex.getSQLState()) {
			
			case "22001":
				/*
				 * Data truncation
				 */
				message = "A quantidade de caracteres excede o permitido.";
				break;

			default:
				message = "Problemas na requisição, favor entrar em contato com o administrador do sistema.";
				break;
			}			
			
		
		} else {
			message = "Problemas na requisição, favor entrar em contato com o administrador do sistema.";
		}

		/*
		 * Set exception parameters set on AutoStore.js file:
		 * successProperty: 'success',
         * messageProperty: 'message'
		 */
		result.put("success", false);
		result.put("message", message);
	}
}
