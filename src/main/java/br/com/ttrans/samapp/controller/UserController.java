package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.service.RoleService;

@Controller
@RequestMapping("/user")
@SuppressWarnings("rawtypes")
public class UserController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/role")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", roleService.getAll());
		
		return result;
	}
	
	/*
	 * CRUD Operations for: Role
	 */
	@RequestMapping("/role/add.action")
	@ResponseBody
	public Map<String,Object> addrole(@RequestBody Map payload, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		/*Implementar isso no Status Rules*/
		
		Role role = new Role();
		
		role.setId((int) payload.get("id"));
		role.setRoleName((String) payload.get("roleName"));
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			roleService.add(role);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/role/update.action")
	@ResponseBody
	public Map<String,Object> updaterole(@RequestBody Role role, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			roleService.edit(role);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/role/delete.action")
	@ResponseBody
	public Map<String,Object> deleterole(@RequestBody Role role, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			roleService.delete(role);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		return result;
	}

}
