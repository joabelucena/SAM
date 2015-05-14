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

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmFilter;
import br.com.ttrans.samapp.model.AlarmGroup;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.service.AlarmFilterService;
import br.com.ttrans.samapp.service.AlarmGroupService;
import br.com.ttrans.samapp.service.AlarmService;
import br.com.ttrans.samapp.service.AlarmTypeService;

@Controller
@RequestMapping("/alarm")
@SuppressWarnings("rawtypes")
public class AlarmController {
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private AlarmGroupService groupService;
	
	@Autowired
	private AlarmTypeService typeService;
	
	@Autowired
	private AlarmFilterService filterService;
	
	/*
	 * Load Data Methods
	 */
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", alarmService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/type")
	@ResponseBody
	public Map loadType() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", typeService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/group")
	@ResponseBody
	public Map loadGroup() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", groupService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/filter")
	@ResponseBody
	public Map loadFilter() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", filterService.loadData());
		
		return result;
	}
	
	
	/*
	 * CRUD Operations for: Alarm
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map addAlarm(@RequestBody Alarm alarm, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			alarmService.add(alarm, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/update.action")
	@ResponseBody
	public Map updateAlarm(@RequestBody Alarm alarm, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			alarmService.edit(alarm, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public Map deleteAlarm(@RequestBody Alarm alarm, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			alarmService.delete(alarm, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	
	/*
	 * CRUD Operations for: AlarmType
	 */
	@RequestMapping("/type/add.action")
	@ResponseBody
	public Map addType(@RequestBody AlarmType type, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			typeService.add(type, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/type/update.action")
	@ResponseBody
	public Map updateType(@RequestBody AlarmType type, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			typeService.edit(type, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/type/delete.action")
	@ResponseBody
	public Map deleteType(@RequestBody AlarmType type, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			typeService.delete(type, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}

	
	/*
	 * CRUD Operations for: AlarmGroup
	 */
	@RequestMapping("/group/add.action")
	@ResponseBody
	public Map addGroup(@RequestBody AlarmGroup group, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			groupService.add(group, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/group/update.action")
	@ResponseBody
	public Map updateGroup(@RequestBody AlarmGroup group, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			groupService.edit(group, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/group/delete.action")
	@ResponseBody
	public Map deleteGroup(@RequestBody AlarmGroup group, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			groupService.delete(group, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	
	/*
	 * CRUD Operations for: AlarmFilter
	 */
	@RequestMapping("/filter/add.action")
	@ResponseBody
	public Map addFilter(@RequestBody AlarmFilter filter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			filterService.add(filter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/filter/update.action")
	@ResponseBody
	public Map updateFilter(@RequestBody AlarmFilter filter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			filterService.edit(filter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/filter/delete.action")
	@ResponseBody
	public Map deleteFilter(@RequestBody AlarmFilter filter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			filterService.delete(filter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}

}
