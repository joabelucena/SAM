package br.com.ttrans.samapp.library;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateBuilder {

	/**
	 * Returns a XMLGregorianCalendar from a java.util.Date
	 * 
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException 
	 */
	public static XMLGregorianCalendar newXMLGregorianCalendarDate(Date date) throws DatatypeConfigurationException {

		GregorianCalendar c = new GregorianCalendar();
		
		c.setTime(date);

		return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

	}
}
