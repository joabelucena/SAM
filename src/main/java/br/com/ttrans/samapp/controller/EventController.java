package br.com.ttrans.samapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;


@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	private EventService service;
	
	private String eventDatetime;
	
	@RequestMapping("/events/")
	@ResponseBody
	public ModelAndView events(Map<String, Object> map){
		//map.put("eventList", service.getAllEvent());
		ModelAndView model = new ModelAndView("events/events/show");
		return model;
	}
	
	
	@RequestMapping("/load")
	@ResponseBody
	public String loadData(Map<String, Object> map) {

		@SuppressWarnings("unchecked")
		List<Event> eventList = service.loadData();
		
		Gson gson = new Gson();
		
		String json = gson.toJson(eventList);
		
		String result = "{\"data\":"+json +"}";
		
		System.out.println(result);
		
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/eventsrestservice", method = RequestMethod.POST)
	public ResponseEntity<Event> get(@Valid @RequestBody Event eventJson, BindingResult result) throws ParseException{
		
		String usr_insert = "SAM_JSON";
		eventJson.setUsr_insert(usr_insert);
		
		if (!result.hasErrors()) {
				
			eventDatetime = eventJson.getEve_date() + " " + eventJson.getEve_time();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date eve_datetime = dateFormat.parse(eventDatetime);
			eventJson.setEve_datetime(eve_datetime);
						
			service.add(eventJson);
	 
			return new ResponseEntity<Event>(HttpStatus.OK);
		
		} else {
			
			return new ResponseEntity<Event>(HttpStatus.NOT_ACCEPTABLE);
			
		}
	}
	
}
