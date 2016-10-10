package br.com.ttrans.samapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.Action;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmFilter;
import br.com.ttrans.samapp.model.AlarmGroup;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.service.AlarmFilterService;
import br.com.ttrans.samapp.service.AlarmGroupService;
import br.com.ttrans.samapp.service.AlarmService;
import br.com.ttrans.samapp.service.AlarmTypeService;

import static br.com.ttrans.samapp.validator.ErrorMessageHandler.*;

@Controller
@RequestMapping("/alarm")
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
	public Map<String, Object> loadData() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", alarmService.loadData());

		return result;
	}

	@RequestMapping("/load/type")
	@ResponseBody
	public Map<String, Object> loadType() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", typeService.loadData());

		return result;
	}

	@RequestMapping("/load/group")
	@ResponseBody
	public Map<String, Object> loadGroup() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", groupService.loadData());

		return result;
	}

	@RequestMapping("/load/filter")
	@ResponseBody
	public Map<String, Object> loadFilter() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", filterService.loadData());

		return result;
	}

	/*
	 * CRUD Operations for: Alarm
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map<String, Object> addAlarm(@RequestBody Alarm alarm, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			alarmService.add(alarm, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.INSERT);
		}

		return result;
	}

	@RequestMapping("/update.action")
	@ResponseBody
	public Map<String, Object> updateAlarm(@RequestBody Alarm alarm, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			alarmService.edit(alarm, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.UPDATE);
		}

		return result;
	}

	@RequestMapping("/delete.action")
	@ResponseBody
	public Map<String, Object> deleteAlarm(@RequestBody Alarm alarm, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			alarmService.delete(alarm, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.DELETE);
		}

		return result;
	}

	/*
	 * CRUD Operations for: AlarmType
	 */
	@RequestMapping("/type/add.action")
	@ResponseBody
	public Map<String, Object> addType(@RequestBody AlarmType type, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			typeService.add(type, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.INSERT);
		}

		return result;
	}

	@RequestMapping("/type/update.action")
	@ResponseBody
	public Map<String, Object> updateType(@RequestBody AlarmType type, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			typeService.edit(type, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.UPDATE);
		}

		return result;
	}

	@RequestMapping("/type/delete.action")
	@ResponseBody
	public Map<String, Object> deleteType(@RequestBody AlarmType type, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			typeService.delete(type, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.DELETE);
		}

		return result;
	}

	/*
	 * CRUD Operations for: AlarmGroup
	 */
	@RequestMapping("/group/add.action")
	@ResponseBody
	public Map<String, Object> addGroup(@RequestBody AlarmGroup group, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			groupService.add(group, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.INSERT);
		}

		return result;
	}

	@RequestMapping("/group/update.action")
	@ResponseBody
	public Map<String, Object> updateGroup(@RequestBody AlarmGroup group, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			groupService.edit(group, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.UPDATE);
		}

		return result;
	}

	@RequestMapping("/group/delete.action")
	@ResponseBody
	public Map<String, Object> deleteGroup(@RequestBody AlarmGroup group, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			groupService.delete(group, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.DELETE);
		}

		return result;
	}

	/*
	 * CRUD Operations for: AlarmFilter
	 */
	@RequestMapping("/filter/add.action")
	@ResponseBody
	public Map<String, Object> addFilter(@RequestBody AlarmFilter filter, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			filterService.add(filter, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.INSERT);
		}

		return result;
	}

	@RequestMapping("/filter/update.action")
	@ResponseBody
	public Map<String, Object> updateFilter(@RequestBody AlarmFilter filter, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			filterService.edit(filter, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.UPDATE);
		}

		return result;
	}

	@RequestMapping("/filter/delete.action")
	@ResponseBody
	public Map<String, Object> deleteFilter(@RequestBody AlarmFilter filter, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			filterService.delete(filter, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result, Action.DELETE);
		}

		return result;
	}

}
