package br.com.ttrans.samapp.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {

		ObjectMapper mapper = new ObjectMapper();
		LoginStatus status = new LoginStatus(false, false, null,
				"Usuario/Senha incorreto(s).");
		OutputStream out = response.getOutputStream();
		mapper.writeValue(out, status);
	}

}
