package com.demo.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Version;
 

@Entity
public class Event implements Serializable {
 
	private static final long serialVersionUID = 9114664664640172512L;
	@Id
	@Column(name = "EVENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  long id;
	
	@Column(name = "DETAILS", nullable = false,length = 100)
	private  String details;

	@Version
    @Column(name = "LAST_UPDATED_TIME")
    private Date updatedTime;
	
	
	protected Event(){}
	public Event(  String details) {
		super();
		this.details = details;
	}
	public Event(long id, String details) {
		super();
		this.id = id;
		this.details = details;
	}

	public long getId() {
		return id;
	}

	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
