package br.com.ttrans.samapp.controller;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.ServiceOrderForecast;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private MessageSource messageSource;
	
	private String eventDatetime;
	
	@RequestMapping("/load")
	@ResponseBody
	public Map loadData() {

		Map<String,Object> result = new HashMap<String, Object>();
		
		result.put("data", eventService.loadData());
		
		return result;
	}
	


	@RequestMapping(value = "/getinfo", method = RequestMethod.POST)
	public Map<String,Object> getInfo(
			@RequestParam(value = "eveId", required = true) long id,
			Authentication authentication, Locale locale){
		
		//Instancia Evento
		Event event = eventService.get(id);
		
		//Instancia Vetor de Tipos de OS permitidos para abertura na tela de Alarmes.
		String[] vType = dao.GetMv("SAM_EVESOTYPE", "").split(";");
		
		//Instancia variaveis de data 
		Calendar start_forecast = Calendar.getInstance();
		Calendar end_forecast = Calendar.getInstance();
		
		//Instancia 'formatador' de data
		Format formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		
		//Instancia os mapas de retorno e criterio para previsão de datas de OS.
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String,Object> crit = new HashMap<String, Object>();
		
		//Mapa de retorno
		result.put("id"					, "");
		result.put("model"				, "");
		result.put("manufacturer"		, "");
		result.put("subsystem"			, "");
		result.put("severity"			, "");
		result.put("datetime"			, "");
		result.put("site"				, "");
		result.put("reco_time"			, "");
		result.put("reco_user"			, "");
		result.put("start_forecast"		, "");
		result.put("end_forecast"		, "");
		result.put("so_type"			, "");
		
		
		
		if(event != null){
			
			//Retorna previsões da OS
			crit.put("system"	, event.getEquipment().getSystem());
			crit.put("severity"	, event.getAlarm().getSeverity());
			
			ServiceOrderForecast forecast = (ServiceOrderForecast) dao.get(ServiceOrderForecast.class, crit);
						
			//Forecast
			if(forecast != null){
				start_forecast.add(Calendar.MINUTE, forecast.getSof_start_forecast());
				end_forecast.add(Calendar.MINUTE, forecast.getSof_end_forecast());
				
				result.put("start_forecast"		, formato.format(start_forecast.getTime()));
				result.put("end_forecast"		, formato.format(end_forecast.getTime()));
			}else{
				result.put("start_forecast"		, formato.format(start_forecast.getTime()));
				result.put("end_forecast"		, formato.format(end_forecast.getTime()));
			}
			
			//Equipment
			if(event.getEquipment() != null){
				result.put("id"					, event.getEquipment().getEqu_id());
				result.put("model"				, event.getEquipment().getModel().getEmo_description());
				result.put("manufacturer"		, event.getEquipment().getManufacturer().getEma_description());
				result.put("subsystem"			, event.getEquipment().getSystem().getSsy_description());
				result.put("site"				, event.getEquipment().getSite().getSit_description());
			}
			
			//Alarm
			if(event.getAlarm() != null){
				result.put("severity"			, event.getAlarm().getSeverity().getSle_description());				
			}
			
			//SO Types
			if(vType.length > 0){
				result.put("so_type"			, vType);
			}
			
			//Event
			result.put("datetime"			, formato.format(event.getEve_datetime()));
			result.put("reco_time"			, formato.format(event.getEve_reco_date()));
			result.put("reco_user"			, event.getEve_reco_user());
			
		}
		return result;
	}
	
	
	@RequestMapping(value = "/recognize", method = RequestMethod.POST)
	public String recognize(
			@RequestParam(value = "recognizeId", required = false) long[] ids,
			Authentication authentication, Locale locale){

		for (int i = 0; i < ids.length; i++) {

			Event event = eventService.get(ids[i]);

			//Verifica se achou o evento
			if (event != null) {

				//Verifica se ja foi reconhecido
				if (event.getEve_reco_user() == null) {

					event.setEve_reco_user(authentication.getName());
					event.setEve_reco_date(new Date());

					eventService.edit(event, authentication);
				}

			}
		}

		return messageSource.getMessage("responseStatus.Ok", null, locale);
	}

	@ResponseBody
	@RequestMapping(value = "/eventsrestservice", method = RequestMethod.POST)
	public ResponseEntity<Event> get(@Valid @RequestBody Event eventJson,
			BindingResult result) throws ParseException {

		String usr_insert = "SAM_JSON";
		eventJson.setUsr_insert(usr_insert);

		// validator.validate(eventJson, result, "add");

		if (!result.hasErrors()) {

			eventDatetime = eventJson.getEve_date() + " "
					+ eventJson.getEve_time();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			Date eve_datetime = dateFormat.parse(eventDatetime);
			eventJson.setEve_datetime(eve_datetime);

			eventService.add(eventJson);

			return new ResponseEntity<Event>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Event>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}