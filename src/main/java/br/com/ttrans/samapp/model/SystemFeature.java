package br.com.ttrans.samapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Sys_Features")
@SequenceGenerator(name = "INC_SYS_FEATURES", sequenceName = "GEN_FTR_ID")
public class SystemFeature {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="INC_SYS_FEATURES") 
	private int id;
	
	@Column
	private String description;
	
	@Column
	private String url;

	
	public SystemFeature(String url) {
		super();
		this.url = url;
	}
	
	public SystemFeature(){}
	public SystemFeature(int id, String description, String url) {
		super();
		this.id = id;
		this.description = description;
		this.url = url;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemFeature other = (SystemFeature) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
}
