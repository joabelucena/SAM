package br.com.ttrans.samapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.OperationalState;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;

import com.google.gson.Gson;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private  MessageSource messageSource;
	
	private String eventDatetime;
	
	@Deprecated
	@RequestMapping("/events/")
	public ModelAndView events(Map<String, Object> map){
		
		ModelAndView model = new ModelAndView("events/events/show");
		
		return model;
	}
	
	@RequestMapping("/load")
	@ResponseBody
	public String loadData(Map<String, Object> map) {
		
		List eventList = eventService.loadData();
		
		Gson gson = new Gson();
		
		String json = gson.toJson(eventList);
		
		String result = "{\"data\":"+json +"}";
		
		System.out.println(result);
				
		return result;
	}
	
	@RequestMapping(value = "/recognize/{id}", method = RequestMethod.POST)
	public String recognize(
			@PathVariable("id") long id
			,Authentication authentication
			,Locale locale) {
		
		Event event = eventService.get(id);
		
		if (!(event == null)){
			
			event.setEve_reco_user(authentication.getName());
			event.setEve_reco_date(new Date());
			
			eventService.edit(event, authentication);
			
			return messageSource.getMessage("responseStatus.Ok", null,locale);
		}
		
		return messageSource.getMessage("responseStatus.Failure", null,locale);
	}
	
	@ResponseBody
	@RequestMapping(value = "/eventsrestservice", method = RequestMethod.POST)
	public ResponseEntity<Event> get(@Valid @RequestBody Event eventJson, BindingResult result) throws ParseException{
		
		String usr_insert = "SAM_JSON";
		eventJson.setUsr_insert(usr_insert);
		
		//validator.validate(eventJson, result, "add");
		
		if (!result.hasErrors()) {
				
			eventDatetime = eventJson.getEve_date() + " " + eventJson.getEve_time();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date eve_datetime = dateFormat.parse(eventDatetime);
			eventJson.setEve_datetime(eve_datetime);
						
			eventService.add(eventJson);
	 
			return new ResponseEntity<Event>(HttpStatus.OK);
		
		} else {
			return new ResponseEntity<Event>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}