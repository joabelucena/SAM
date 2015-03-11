package br.com.ttrans.samapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmFilter;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.service.AlarmFilterService;
import br.com.ttrans.samapp.service.AlarmService;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.validator.ResponseStatus;

@Controller
@RequestMapping("/alarmfilter")
public class AlarmFilterController {
	
	@Autowired
	private AlarmFilterService alarmFilterService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private AlarmService alarmService;
	
	@RequestMapping("/new")
	@ResponseBody
	public ResponseStatus newAlarmFilter(
			@RequestParam(value="almId", required = true) String almId,
			@RequestParam(value="equId", required = true) String equId,
			Authentication authentication) {
		
		//Recupera alarme
		Alarm alarm = alarmService.get(almId);
		
		//Recupera equipamentoqui 
		Equipment equipment = equipmentService.get(equId);
		
		//Cria objeto AlarmFilter
		AlarmFilter alarmFilter = new AlarmFilter();
		
		alarmFilter.setAlarm(alarm);
		alarmFilter.setEquipment(equipment);
		
		if(equipment == null || alarm == null){
			return ResponseStatus.NULLPOINTER;
		}else{
			alarmFilterService.add(alarmFilter, authentication);
		}
		
		return ResponseStatus.OK;
	}
	
	@RequestMapping("/del")
	public ResponseStatus delAlarmFilter(
			Authentication authentication) {
		
		
		
		return ResponseStatus.OK;
	}
	
}
