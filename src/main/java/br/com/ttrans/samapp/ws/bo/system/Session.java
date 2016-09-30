package br.com.ttrans.samapp.ws.bo.system;

import java.util.Date;

import br.com.ttrans.samapp.library.IP;

public class Session {

	private Connection connection;
	
	private Date alive;
	
	private IP ip;

	public Session(Connection connection, Date alive, IP ip) {
		super();
		this.connection = connection;
		this.alive = alive;
		this.ip = ip;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Date getAlive() {
		return alive;
	}

	public void setAlive(Date alive) {
		this.alive = alive;
	}

	public IP getIP() {
		return ip;
	}

	public void setIP(IP ip) {
		this.ip = ip;
	}
	
	
}