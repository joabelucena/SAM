package br.com.ttrans.samapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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