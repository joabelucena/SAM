package br.com.ttrans.samapp.ws.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.ttrans.samapp.library.WebInfo;

@WebInfo(name="CustomerService",
		description="Serviço para retornar os clientes cadastrados.",
		url="/services/customer")
@WebService(serviceName = "customerService")
public class CustomerEndpoint {
	
	@WebMethod(operationName="getAlarm")
	public String getCustomerById(String customerId) {
		
		return "costumeiro";
	}
}
