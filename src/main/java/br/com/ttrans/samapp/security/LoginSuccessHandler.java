package br.com.ttrans.samapp.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		LoginStatus status = new LoginStatus(true, auth.isAuthenticated(),
				auth.getName(), null);
		OutputStream out = response.getOutputStream();
		mapper.writeValue(out, status);

	}

}
