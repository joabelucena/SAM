package br.com.ttrans.samapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.firebirdsql.jdbc.FBSQLException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.EquipmentManufacturer;
import br.com.ttrans.samapp.model.EquipmentModel;
import br.com.ttrans.samapp.model.EquipmentProtocol;
import br.com.ttrans.samapp.model.EquipmentType;
import br.com.ttrans.samapp.model.OperationalState;
import br.com.ttrans.samapp.model.SubSystem;
import br.com.ttrans.samapp.service.EquipmentManufacturerService;
import br.com.ttrans.samapp.service.EquipmentModelService;
import br.com.ttrans.samapp.service.EquipmentProtocolService;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EquipmentTypeService;
import br.com.ttrans.samapp.service.OperationalStateService;
import br.com.ttrans.samapp.service.SubSystemService;

import static br.com.ttrans.samapp.validator.ErrorMessageHandler.*;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private EquipmentProtocolService protocolService;

	@Autowired
	private OperationalStateService operationalStateService;

	@Autowired
	private SubSystemService systemService;

	@Autowired
	private EquipmentManufacturerService manufacturerService;

	@Autowired
	private EquipmentModelService modelService;

	@Autowired
	private EquipmentTypeService typeService;

	@Autowired
	private DAO dao;

	/*
	 * Load Data Methods
	 */
	@RequestMapping("/load")
	@ResponseBody
	public Map<String, Object> loadData(@RequestParam(value = "start", required = true) int start,
			@RequestParam(value = "limit", required = true) int limit) {

		Map<String, Object> result = new HashMap<String, Object>();

		List<Equipment> data = equipmentService.loadData(start, limit);

		result.put("data", data);
		result.put("total", dao.rowCount(Equipment.class, null));

		return result;
	}

	@RequestMapping("/load/manufacturer")
	@ResponseBody
	public Map<String, Object> loadManufacturer() {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", manufacturerService.loadData());
		result.put("total", dao.rowCount(EquipmentManufacturer.class, null));

		return result;
	}

	@RequestMapping("/load/type")
	@ResponseBody
	public Map<String, Object> loadType() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", typeService.loadData());

		return result;
	}

	@RequestMapping("/load/model")
	@ResponseBody
	public Map<String, Object> loadModel() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", modelService.loadData());

		return result;
	}

	@RequestMapping("/load/protocol")
	@ResponseBody
	public Map<String, Object> loadProtocol() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", protocolService.loadData());

		return result;
	}

	@RequestMapping("/load/operationalState")
	@ResponseBody
	public Map<String, Object> operationalState() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", operationalStateService.loadData());

		return result;
	}

	@RequestMapping("/load/system")
	@ResponseBody
	public Map<String, Object> system() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("data", systemService.loadData());

		return result;
	}

	/*
	 * CRUD Operations for: Equipments
	 */
	@RequestMapping("/add.action")
	@ResponseBody
	public Map<String, Object> addAlarm(@RequestBody Equipment equipment, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			equipmentService.add(equipment, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/update.action")
	@ResponseBody
	public Map<String, Object> updateAlarm(@RequestBody Equipment equipment, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			equipmentService.edit(equipment, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/delete.action")
	@ResponseBody
	public Map<String, Object> deleteAlarm(@RequestBody Equipment equipment, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			equipmentService.delete(equipment, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}
		
		return result;
	}

	/*
	 * CRUD Operations for: EquipmentManufacturer
	 */
	@RequestMapping("/manufacturer/add.action")
	@ResponseBody
	@ExceptionHandler({ SQLException.class, HibernateException.class, FBSQLException.class })
	public Map<String, Object> addManufacturer(@RequestBody EquipmentManufacturer manufacturer,
			HttpServletRequest request, Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			manufacturerService.add(manufacturer, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/manufacturer/update.action")
	@ResponseBody
	public Map<String, Object> updateManufacturer(@RequestBody EquipmentManufacturer manufacturer, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			manufacturerService.edit(manufacturer, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/manufacturer/delete.action")
	@ResponseBody
	public Map<String, Object> deleteManufacturer(@RequestBody EquipmentManufacturer manufacturer, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			manufacturerService.delete(manufacturer, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	/*
	 * CRUD Operations for: EquipmentModel
	 */
	@RequestMapping("/model/add.action")
	@ResponseBody
	public Map<String, Object> addModel(@RequestBody EquipmentModel model, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			modelService.add(model, authentication);
			result.put("success", true);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;

	}

	@RequestMapping("/model/update.action")
	@ResponseBody
	public Map<String, Object> updateModel(@RequestBody EquipmentModel model, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			modelService.edit(model, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/model/delete.action")
	@ResponseBody
	public Map<String, Object> deleteModel(@RequestBody EquipmentModel model, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			modelService.delete(model, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	/*
	 * CRUD Operations for: EquipmentType
	 */
	@RequestMapping("/type/add.action")
	@ResponseBody
	public Map<String, Object> addModel(@RequestBody EquipmentType type, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			typeService.add(type, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/type/update.action")
	@ResponseBody
	public Map<String, Object> updateModel(@RequestBody EquipmentType type, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			typeService.edit(type, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/type/delete.action")
	@ResponseBody
	public Map<String, Object> deleteModel(@RequestBody EquipmentType type, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			typeService.delete(type, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	/*
	 * CRUD Operations for: Protocol
	 */
	@RequestMapping("/protocol/add.action")
	@ResponseBody
	public Map<String, Object> addModel(@RequestBody EquipmentProtocol protocol, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			protocolService.add(protocol, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/protocol/update.action")
	@ResponseBody
	public Map<String, Object> updateModel(@RequestBody EquipmentProtocol protocol, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			protocolService.edit(protocol, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/protocol/delete.action")
	@ResponseBody
	public Map<String, Object> deleteModel(@RequestBody EquipmentProtocol protocol, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			protocolService.delete(protocol, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	/*
	 * CRUD Operations for: Operational State
	 */
	@RequestMapping("/operationalState/add.action")
	@ResponseBody
	public Map<String, Object> addModel(@RequestBody OperationalState operationalState, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			operationalStateService.add(operationalState, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/operationalState/update.action")
	@ResponseBody
	public Map<String, Object> updateModel(@RequestBody OperationalState operationalState, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			operationalStateService.edit(operationalState, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/operationalState/delete.action")
	@ResponseBody
	public Map<String, Object> deleteModel(@RequestBody OperationalState operationalState, HttpServletRequest request,
			Authentication authentication, HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			operationalStateService.delete(operationalState, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	/*
	 * CRUD Operations for: System
	 */
	@RequestMapping("/system/add.action")
	@ResponseBody
	public Map<String, Object> addModel(@RequestBody SubSystem system, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			systemService.add(system, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/system/update.action")
	@ResponseBody
	public Map<String, Object> updateModel(@RequestBody SubSystem system, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			systemService.edit(system, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

	@RequestMapping("/system/delete.action")
	@ResponseBody
	public Map<String, Object> deleteModel(@RequestBody SubSystem system, HttpServletRequest request, Authentication authentication,
			HttpServletResponse response) {

		// Result Map
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			systemService.delete(system, authentication);
		} catch (DataAccessException e) {
			getUserMessage(e, result);
		}

		return result;
	}

}
