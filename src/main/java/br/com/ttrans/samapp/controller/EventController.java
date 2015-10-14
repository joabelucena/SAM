package br.com.ttrans.samapp.controller;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import br.com.ttrans.samapp.service.EventService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

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
		String[] vType = dao.getMv("SAM_EVESOTYPE", "").split(";");
		
		String[][] types = new String[vType.length][1];
		
		for (int i=0; i<types.length; i++){
			types[i][0] = vType[i];
		}

		     
	
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
		
		List<HashMap> a = new LinkedList<HashMap>();
		
		HashMap<String,Object> typeAux 	= new HashMap<String, Object>();
		
		for(int i = 0; i < vType.length; i++){
			typeAux.put("type", vType[i]);
			a.add(typeAux);
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
					start_forecast.add(Calendar.MINUTE, forecast.getStartForecast());
					end_forecast.add(Calendar.MINUTE, forecast.getEndForecast());
					
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
				result.put("id"					, event.getEquipment().getId());
				result.put("model"				, event.getEquipment().getModel().getDesc());
				result.put("manufacturer"		, event.getEquipment().getManufacturer().getDesc());
				result.put("subsystem"			, event.getEquipment().getSystem().getDesc());
				result.put("site"				, event.getEquipment().getSite().getDesc());
			}catch(Exception e){
				
			}
			
			
			//Alarm
			try{
				result.put("severity_id"			, event.getAlarm().getSeverity().getId());
				result.put("severity_desc"			, event.getAlarm().getSeverity().getDesc());
			
			}catch(Exception e){
				
			}
						
			//SO Types
			result.put("so_type"			, types);
			
			//Event
			try{
				result.put("datetime", formato.format(event.getDatetime()));
			} catch (Exception e) {

			}
			try{
				result.put("reco_time"			, formato.format(event.getRecoDate()));
				result.put("reco_user"			, event.getRecoUser());
			}catch(Exception e){
				
			}
			
		}
		
		return new ResponseEntity< Map<String,Object> >(result, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "action/recognize", method = RequestMethod.POST)
	public Map<String, Object> recognize(
			@RequestParam(value = "recognizeId", required = false) Long[] ids,
			Authentication authentication, Locale locale){
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();

		try{
			eventService.recognize(ids, authentication);
		}catch(Exception e){
			result.put("error", e.getMessage());
		}
				
		return result;
	}
	
	@RequestMapping(value = "action/normalize", method = RequestMethod.POST)
	public Map<String, Object> normalize(
			@RequestParam(value = "normalizeId", required = false) Long id,
			Authentication authentication, Locale locale){
		
		//Result Map
		Map<String,Object> result = new HashMap<String, Object>();
		
		Event ev = eventService.get(id);
		
		if(ev instanceof Event){
			
			if(ev.getAlarm().getManNorm().equals("Y")){
			
				try{
					eventService.normalize(id, authentication);
				}catch(Exception e){
					result.put("error", e.getMessage());
				}
			}else{
				result.put("message", messageSource.getMessage("response.event.NotNormalizable", null, locale));
			}
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/eventsrestservice", method = RequestMethod.POST)
	public ResponseEntity<String> get(@Valid @RequestBody Event eventJson,
			BindingResult result, Locale locale) throws ParseException {

		String usr_insert = "SAM_JSON";
		eventJson.setInsert(usr_insert);

		// validator.validate(eventJson, result, "add");

		if (!result.hasErrors()) {

			eventDatetime = eventJson.getDate() + " "
					+ eventJson.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			Date eve_datetime = dateFormat.parse(eventDatetime);
			eventJson.setDatetime(eve_datetime);

			eventService.add(eventJson);

			return new ResponseEntity<String>(messageSource.getMessage("response.Ok", null, locale) , HttpStatus.BAD_REQUEST);

			

		} else {
			return new ResponseEntity<String>(messageSource.getMessage("response.Failure", null, locale) , HttpStatus.BAD_REQUEST);
		}
	}
}