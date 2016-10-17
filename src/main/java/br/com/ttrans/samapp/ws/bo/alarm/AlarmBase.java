//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.24 at 10:26:12 AM BRT 
//


package br.com.ttrans.samapp.ws.bo.alarm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         - creatorId: Business application identifier which creates alarm message - shared configuration
 *         - sessionInstanceId: unique session identifier given by SOA Framework - runtime
 *         - timeStamp: UTC date when alarm message is sending (with milliseconds) - runtime
 *         - alarmInstanceId: unique alarm identifier created by Business application following a specific pattern - runtime
 *       
 * 
 * <p>Java class for AlarmBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AlarmBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creatorId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sessionInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="alarmInstanceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlarmBase", propOrder = {
    "creatorId",
    "sessionInstanceId",
    "timeStamp",
    "alarmInstanceId"
})
@XmlSeeAlso({
    AlarmRequestAck.class,
    AlarmUpdateState.class,
    AlarmRefresh.class,
    AlarmDetail.class,
    AlarmDelete.class
})
public class AlarmBase {

    @XmlElement(required = true)
    protected String creatorId;
    protected String sessionInstanceId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlElement(required = true)
    protected String alarmInstanceId;

    /**
     * Custom constructors
     */
    public AlarmBase() {
    	super();
    }

    public AlarmBase(AlarmBase obj) {
		super();
		this.creatorId = obj.creatorId;
		this.sessionInstanceId = obj.sessionInstanceId;
		this.timeStamp = obj.timeStamp;
		this.alarmInstanceId = obj.alarmInstanceId;
	}

	/**
     * Gets the value of the creatorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * Sets the value of the creatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatorId(String value) {
        this.creatorId = value;
    }

    /**
     * Gets the value of the sessionInstanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionInstanceId() {
        return sessionInstanceId;
    }

    /**
     * Sets the value of the sessionInstanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionInstanceId(String value) {
        this.sessionInstanceId = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the alarmInstanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmInstanceId() {
        return alarmInstanceId;
    }

    /**
     * Sets the value of the alarmInstanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmInstanceId(String value) {
        this.alarmInstanceId = value;
    }

    /**
     * Returns a human readable version of the object
     */
	@Override
	public String toString() {
		return "AlarmBase [creatorId=" + creatorId + ", sessionInstanceId=" + sessionInstanceId + ", timeStamp="
				+ timeStamp + ", alarmInstanceId=" + alarmInstanceId + "]";
	}
}
