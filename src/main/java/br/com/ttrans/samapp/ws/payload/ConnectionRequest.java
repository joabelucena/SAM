package br.com.ttrans.samapp.ws.payload;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace =  "http://localhost/systemService", name = "ConnectionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectionRequest {
	
	@XmlElement
	private String creatorId;
	private Date timeStamp;
	
	public ConnectionRequest() {
		super();
	}
	
	public ConnectionRequest(String creatorId, Date timeStamp) {
		super();
		this.creatorId = creatorId;
		this.timeStamp = timeStamp;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
