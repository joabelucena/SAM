package br.com.ttrans.samapp.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides Web Services Information that will be displayed on /services information page. 
 * 
 * This annotation will be scanned for classes @ 'br.com.ttrans.samapp.ws.endpoint' package.  
 * 
 * @author Joabe Lucena
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebInfo {
	
	
	/**
	 * Describes the Web Service name
	 * @return Web Service name
	 */
	String name() default "";
	
	/**
	 * Web Service context path. Eg: localhost:8080/AppName/services/hello then url="/services/hello". This URL must be the 
	 * same of /CLASSPATH/WEB-INF/spring/appServlet/wss-servlet.xml.
	 * @return Web Service URL
	 */
	String url() default "";
	
	/**
	 * Briefly description of Web Service.
	 * @return Web Service description
	 */
	String description() default "";

}
