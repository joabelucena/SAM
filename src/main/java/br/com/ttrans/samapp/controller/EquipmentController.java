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

import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.EquipmentManufacturer;
import br.com.ttrans.samapp.model.EquipmentModel;
import br.com.ttrans.samapp.model.EquipmentProtocol;
import br.com.ttrans.samapp.model.EquipmentType;
import br.com.ttrans.samapp.model.OperationalState;
import br.com.ttrans.samapp.model.SubSystem;
import br.com.ttrans.samapp.service.CounterService;
import br.com.ttrans.samapp.service.EquipmentManufacturerService;
import br.com.ttrans.samapp.service.EquipmentModelService;
import br.com.ttrans.samapp.service.EquipmentProtocolService;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EquipmentTypeService;
import br.com.ttrans.samapp.service.OperationalStateService;
import br.com.ttrans.samapp.service.SubSystemService;

@Controller
@RequestMapping("/equipment")
@SuppressWarnings("rawtypes")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private CounterService counterService;
	
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
	
	
	/*
	 * Load Data Methods
	 */
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", equipmentService.loadData());
		
		return result;
	}

	@RequestMapping("/load/manufacturer")
	@ResponseBody
	public Map loadManufacturer() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", manufacturerService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/type")
	@ResponseBody
	public Map loadType() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", typeService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/model")
	@ResponseBody
	public Map loadModel() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", modelService.loadData());
		
		return result;
	}	
	
	@RequestMapping("/load/counter")
	@ResponseBody
	public Map loadCounter() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", counterService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/protocol")
	@ResponseBody
	public Map loadProtocol() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", protocolService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/operationalState")
	@ResponseBody
	public Map operationalState() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", operationalStateService.loadData());
		
		return result;
	}
	
	@RequestMapping("/load/system")
	@ResponseBody
	public Map system() {
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", systemService.loadData());
		
		return result;
	}
	
	
	/*
	 * CRUD Operations for: EquipmentManufacturer
	 */
	@RequestMapping("/manufacturer/add.action")
	@ResponseBody
	public Map addManufacturer(@RequestBody EquipmentManufacturer manufacturer, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			manufacturerService.add(manufacturer, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/manufacturer/update.action")
	@ResponseBody
	public Map updateManufacturer(@RequestBody EquipmentManufacturer manufacturer, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			manufacturerService.edit(manufacturer, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/manufacturer/delete.action")
	@ResponseBody
	public Map deleteManufacturer(@RequestBody EquipmentManufacturer manufacturer, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			manufacturerService.delete(manufacturer, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	

	/*
	 * CRUD Operations for: EquipmentModel
	 */
	@RequestMapping("/model/add.action")
	@ResponseBody
	public Map addModel(@RequestBody EquipmentModel model, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response){
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			modelService.add(model, authentication);
			result.put("success",true);
		}catch(Exception e){
			result.put("message","teste de retorno de excecao");
			result.put("success",false);
		}
		
		
		return result;

	}
	
	@RequestMapping("/model/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody EquipmentModel model, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response){
		
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			modelService.edit(model, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/model/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody EquipmentModel model, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			modelService.delete(model, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	

	/*
	 * CRUD Operations for: EquipmentType
	 */
	@RequestMapping("/type/add.action")
	@ResponseBody
	public Map addModel(@RequestBody EquipmentType type, 
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
	public Map updateModel(@RequestBody EquipmentType type, 
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
	public Map deleteModel(@RequestBody EquipmentType type, 
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
	 * CRUD Operations for: Counter
	 */
	@RequestMapping("/counter/add.action")
	@ResponseBody
	public Map addModel(@RequestBody Counter counter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			counterService.add(counter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/counter/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody Counter counter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			counterService.edit(counter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/counter/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody Counter counter, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			counterService.delete(counter, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	/*
	 * CRUD Operations for: Protocol
	 */
	@RequestMapping("/protocol/add.action")
	@ResponseBody
	public Map addModel(@RequestBody EquipmentProtocol protocol, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			protocolService.add(protocol, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/protocol/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody EquipmentProtocol protocol, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			protocolService.edit(protocol, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/protocol/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody EquipmentProtocol protocol, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			protocolService.delete(protocol, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	/*
	 * CRUD Operations for: Operational State
	 */
	@RequestMapping("/operationalState/add.action")
	@ResponseBody
	public Map addModel(@RequestBody OperationalState operationalState, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			operationalStateService.add(operationalState, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/operationalState/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody OperationalState operationalState, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			operationalStateService.edit(operationalState, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/operationalState/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody OperationalState operationalState, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			operationalStateService.delete(operationalState, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	/*
	 * CRUD Operations for: System
	 */
	@RequestMapping("/system/add.action")
	@ResponseBody
	public Map addModel(@RequestBody SubSystem system, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			systemService.add(system, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping("/system/update.action")
	@ResponseBody
	public Map updateModel(@RequestBody SubSystem system, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			systemService.edit(system, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}
	
	@RequestMapping("/system/delete.action")
	@ResponseBody
	public Map deleteModel(@RequestBody SubSystem system, 
			HttpServletRequest request,
			Authentication authentication,
            HttpServletResponse response) {
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			systemService.delete(system, authentication);
		}catch(Exception e){
			result.put("message",e.getMessage());
		}

		
		return result;
	}

}
