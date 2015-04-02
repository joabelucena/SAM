package br.com.ttrans.samapp.controller;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

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
	public ResponseEntity< Map<String,Object> >getInfo(
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
		
		//Instancia objeto para previsoes da OS
		ServiceOrderForecast forecast = null;
		
		//Instancia os mapas de retorno e criterio para previsão de datas de OS.
		Map<String,Object> result 	= new HashMap<String, Object>();
		Map<String,Object> crit 	= new HashMap<String, Object>();
		Map<String,Object> types 	= new TreeMap<String, Object>();
		
		for(int i = 0; i < vType.length; i++){
			types.put("type", vType[i]);
		}
		
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
			try{
				crit.put("system"	, event.getEquipment().getSystem());
				crit.put("severity"	, event.getAlarm().getSeverity());
	
				forecast = (ServiceOrderForecast) dao.get(ServiceOrderForecast.class, crit);
			}catch(Exception e){
				
			}

			
			try{
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
			}catch(Exception e){
				
			}
			
			//Equipment			
			try{
				result.put("id"					, event.getEquipment().getEqu_id());
				result.put("model"				, event.getEquipment().getModel().getEmo_description());
				result.put("manufacturer"		, event.getEquipment().getManufacturer().getEma_description());
				result.put("subsystem"			, event.getEquipment().getSystem().getSsy_description());
				result.put("site"				, event.getEquipment().getSite().getSit_description());
			}catch(Exception e){
				
			}
			
			
			//Alarm
			try{
				result.put("severity"			, event.getAlarm().getSeverity().getSle_description());				
			
			}catch(Exception e){
				
			}
						
			//SO Types
			result.put("so_type"			, types);
			
			//Event
			try{
				result.put("datetime"			, formato.format(event.getEve_datetime()));
				result.put("reco_time"			, formato.format(event.getEve_reco_date()));
				result.put("reco_user"			, event.getEve_reco_user());
			}catch(Exception e){
				
			}
			
		}
		
		return new ResponseEntity< Map<String,Object> >(result, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "action/recognize", method = RequestMethod.POST)
	public ResponseEntity<String> recognize(
			@RequestParam(value = "recognizeId", required = false) Long[] ids,
			Authentication authentication, Locale locale){

		try{
			eventService.recognize(ids, authentication);
		}catch(Exception e){
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale) , HttpStatus.BAD_REQUEST);
		}
				
		return new ResponseEntity<String>(messageSource.getMessage("response.Ok", null, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "action/normalize", method = RequestMethod.POST)
	public ResponseEntity<String> normalize(
			@RequestParam(value = "normalizeId", required = false) Long id,
			Authentication authentication, Locale locale){
		
		try{
			eventService.normalize(id, authentication);
		}catch(Exception e){
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale) , HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(messageSource.getMessage("response.Ok", null, locale), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/eventsrestservice", method = RequestMethod.POST)
	public ResponseEntity<String> get(@Valid @RequestBody Event eventJson,
			BindingResult result, Locale locale) throws ParseException {

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

			return new ResponseEntity<String>(messageSource.getMessage("response.Ok", null, locale) , HttpStatus.BAD_REQUEST);

			

		} else {
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale) , HttpStatus.BAD_REQUEST);
		}
	}
}