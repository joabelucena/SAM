package br.com.ttrans.samapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EquipmentService;
import br.com.ttrans.samapp.service.EventService;
import br.com.ttrans.samapp.validator.ResponseStatus;

import com.google.gson.Gson;


@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EquipmentService equipmentService;
	
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
		
		@SuppressWarnings("unchecked")
		List eventList = eventService.loadData();
		
		Gson gson = new Gson();
		
		String json = gson.toJson(eventList);
		
		String result = "{\"data\":"+json +"}";
		
		System.out.println(result);
				
		return result;
	}
	
	@RequestMapping(value = "/recognize/{id}", method = RequestMethod.POST)
	
	public ResponseStatus recognize(
			@PathVariable("id") int id
			,Authentication authentication) {
		
		Event event = eventService.get(id);
		
		if (!(event == null) && event.getEve_reco_user() == null){
			
			event.setEve_reco_user(authentication.getName());
			event.setEve_reco_date(new Date());
			
			eventService.edit(event, authentication);
			
			return ResponseStatus.OK;
		}
		
		return ResponseStatus.TEST;
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