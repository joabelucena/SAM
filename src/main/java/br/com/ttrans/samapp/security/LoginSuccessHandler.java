package br.com.ttrans.samapp.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.UserDao;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Scope("session")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		LoginStatus status = new LoginStatus(true, auth.isAuthenticated(),
				auth.getName(), null);
		OutputStream out = response.getOutputStream();
		
		//Carrega na sessão ativa o objeto com os dados do usuario logado.
		request.getSession().setAttribute("loggedUser", userDao.findUserByName(auth.getName()));
		
		mapper.writeValue(out, status);
	}
}