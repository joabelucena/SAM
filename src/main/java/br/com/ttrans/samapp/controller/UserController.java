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
import br.com.ttrans.samapp.model.User;
import br.com.ttrans.samapp.service.RoleService;
import br.com.ttrans.samapp.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/load")
	@ResponseBody
	public Map<String,Object> loadUser() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", userService.loadData());
		
		return result;
	}
	
	
	@RequestMapping("/load/role")
	@ResponseBody
	public Map<String,Object> loadRole() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", roleService.getAll());
		
		return result;
	}
	
	/*
	 * CRUD Operations for: User
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map<String,Object> addUser(@RequestBody User user, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			userService.add(user, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map<String,Object> updateUser(@RequestBody User user, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			userService.edit(user, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public Map<String,Object> deleteUser(@RequestBody User user, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			userService.delete(user, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		return result;
	}

	/*
	 * CRUD Operations for: Role
	 */
	@RequestMapping("/role/add.action")
	@ResponseBody
	public Map<String,Object> addRole(@RequestBody Role role, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
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
