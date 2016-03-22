package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.ttrans.samapp.library.WebInfo;
import br.com.ttrans.samapp.ws.bo.system.*;

@WebInfo(name = "SystemServices",
			description = "Este serviço provê um amigável Hello para quem o executa kkk oh la, não ta dando assim",
			url = "/services/Maestro/SystemServices")
@WebService
public interface SystemEndpoint {

	@WebMethod public void Connection(Connection payload);
	
	@WebMethod public void SessionDetail(SessionDetail payload);

}
