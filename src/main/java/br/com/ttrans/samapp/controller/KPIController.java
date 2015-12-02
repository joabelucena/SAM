package br.com.ttrans.samapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ttrans.samapp.model.EquipmentManufacturer;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.service.ServiceOrderService;

@Controller
@RequestMapping("/kpi")
public class KPIController {
	
	@Autowired
	private ServiceOrderService service;
	
	@RequestMapping("all")
	public Map<String, Object> getMtbf(@RequestBody EquipmentManufacturer manufacturer, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response){
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<ServiceOrder> orders = new ArrayList<ServiceOrder>(service.loadData());
		
		Collections.sort(orders, new Comparator<ServiceOrder>() {
		    public int compare(ServiceOrder o1, ServiceOrder o2) {
		        int i1 = o1.getId();
		        int i2 = o2.getId();
		        
		        return (i1 < i2 ? -1 : (i1 == i2 ? 0 : 1));
		    }
		});
		
		for(ServiceOrder order : orders){
			/**
			 * TODO INserir calculo dos KPIs aquiii
			 */
		}
		
		return result;
	}


}
