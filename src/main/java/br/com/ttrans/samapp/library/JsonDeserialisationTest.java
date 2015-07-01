package br.com.ttrans.samapp.library;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.model.ServiceStation;
import br.com.ttrans.samapp.model.Users;

public class JsonDeserialisationTest
{

    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    @Test
    public void allClassesUsedByOurControllersShouldBeDeserialisableByJackson() throws Exception
    {
    	assertCanBeMapped(Role.class);
    	assertCanBeMapped(Users.class);
    	assertCanBeMapped(Menu.class);
    	assertCanBeMapped(ServiceStation.class);    	
    	assertCanBeMapped(Alarm.class);
    }

    private void assertCanBeMapped(Class<?> classToTest){
    	
        if(converter.canRead(classToTest, MediaType.APPLICATION_JSON)){
        	System.out.println(classToTest+": YES!!!.");
        }else{
        	System.out.println(classToTest+": NO!!!.");
        }
    }

}