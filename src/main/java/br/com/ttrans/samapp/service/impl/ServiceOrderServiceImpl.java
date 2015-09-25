package br.com.ttrans.samapp.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.dao.ServiceOrderStatusDao;
import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.ServiceOrderLog;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.service.ServiceOrderService;

@Repository
public class ServiceOrderServiceImpl implements ServiceOrderService {

	@Autowired
	private ServiceOrderDao dao;
	
	@Autowired
	private DAO lib;
	
	@Autowired
	private ServiceOrderStatusDao statusDao;
	

	@Transactional
	public int add(ServiceOrder so, Authentication authentication) {
		
		//Retorna status inicial cadastrado em parametro
		ServiceOrderStatus status = statusDao.findByName(lib.getMv("SAM_SOSTATUS", "NOVA")); 
		
		//Atualiza OS com o status incial
		so.setStatus(status);
		
		//Cria objeto de log
		Set<ServiceOrderLog> log = new HashSet<ServiceOrderLog>();
		
		log.add( new ServiceOrderLog(	status,						//Status De 
										status,						//Status Para	
										authentication.getName(),	//Usuario
										new Date(),					//Data/Hora
										so.getRemark(),				//Observacao						
										authentication.getName()));	//Usuario inserção (USR_INSERT)
		
		//'Seta' log na OS
		so.setLog(log);
		
		return dao.add(so, authentication);
		
	}

	@Transactional
	public void edit(ServiceOrder so, Authentication authentication) {
		
		if(so.statusChanged()){
			
			so.getLog().add(new ServiceOrderLog(	so.getLastLog().getCurstatus(),		//Status De 
													so.getStatus(),						//Status Para	
													authentication.getName(),			//Usuario
													new Date(),							//Data/Hora
													so.getLogRemark(),					//Observacao						
													authentication.getName()));			//Usuario inserção (USR_INSERT)
			
		}
		
		dao.edit(so, authentication);
	}

	@Transactional
	public void delete(ServiceOrder so, Authentication authentication) {
		dao.delete(so, authentication);
	}
	
	@Transactional
	public ServiceOrder get(int id) {
		return dao.get(id);
	}

	@Transactional
	public List<ServiceOrder> loadData() {
		return dao.loadData();
	}
}
