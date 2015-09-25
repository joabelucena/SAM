package br.com.ttrans.samapp.ws.system;

import java.util.Date;


public class SessionDetail {


    private String creatorId;

    private String sessionInstanceId;

    private Date timeStamp;
    

	public SessionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public SessionDetail(String creatorId, String sessionInstanceId,
			Date timeStamp) {
		super();
		this.creatorId = creatorId;
		this.sessionInstanceId = sessionInstanceId;
		this.timeStamp = timeStamp;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getSessionInstanceId() {
		return sessionInstanceId;
	}

	public void setSessionInstanceId(String sessionInstanceId) {
		this.sessionInstanceId = sessionInstanceId;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
    
    

}