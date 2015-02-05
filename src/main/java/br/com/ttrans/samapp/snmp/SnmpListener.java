package br.com.ttrans.samapp.snmp;

import javax.servlet.ServletContextEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SnmpListener extends ContextLoaderListener {
	
	@Autowired
	SnmpServer server= new SnmpServer();
	
	@Transactional
	public void contextInitialized(ServletContextEvent event) {
		//super.contextInitialized(event);
		System.out.println("## My web app  Loaded.......");
		
		 WebApplicationContextUtils
	        .getRequiredWebApplicationContext(event.getServletContext())
	        .getAutowireCapableBeanFactory()
	        .autowireBean(this);
		
		server.run();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//super.contextDestroyed(event);
		System.out.println("## My web app  Destroyed.......");
		
		//server.close();
	}
}