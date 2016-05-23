package com.demo.service.event;

import java.util.List;

import com.demo.domain.Event;

public interface EventService {

	Event createEvent(Event evt);
	Event getEvent(Long eventId);
	List<Event> getAll();
	Event updateEvent(Event evt);
	void deleteEvent(Long eventId);
}
