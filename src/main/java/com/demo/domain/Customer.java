package com.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

@Entity
public class Customer implements Serializable{

 
	private static final long serialVersionUID = -7302343928845979333L;
	@Id
	@Column(name = "CUSTOMER_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  long id;
	
	@Column(name = "NAME", nullable = false,length = 50)
	private  String name;
	
	@Version
    @Column(name = "LAST_UPDATED_TIME")
    private Date updatedTime;
	
	@ManyToMany
    @JoinTable(name="CUSTOMER_EVENT",
            joinColumns=
            @JoinColumn(name="CUSTOMER_ID"),
      inverseJoinColumns=
            @JoinColumn(name="EVENT_ID")
    )
	private List<Event> events = new ArrayList<Event>();
		
	protected Customer(){}
	public Customer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
 
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

    public void addEvent(Event evt){
    	if(!getEvents().contains(evt)){
    		getEvents().add(evt);
    	}
    }
    
    public void removeEvent(Event evt){
    	if(getEvents().contains(evt)){
    		getEvents().remove(evt);
    	}
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
