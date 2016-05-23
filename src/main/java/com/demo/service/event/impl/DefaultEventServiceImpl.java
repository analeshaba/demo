package com.demo.service.event.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Customer;
import com.demo.domain.Event;
import com.demo.repository.CustomerRepository;
import com.demo.repository.EventRepository;
import com.demo.service.event.EventService;

@Service("eventService")
@Transactional(readOnly = true)
public class DefaultEventServiceImpl implements EventService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultEventServiceImpl.class);
	
	@Autowired
	EventRepository eventRepository;

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Event createEvent(Event evt) {
		evt = eventRepository.save(evt);
		return evt;
	}

	@Override
	public Event getEvent(Long eventId) {
		logger.info("Finding event by id: {}", eventId);
		return eventRepository.findOne(eventId);
	}

	@Override

	public List<Event> getAll() {
		logger.info("Finding  all events by");
		return  (List<Event>) eventRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Event updateEvent(Event evt) {
		Event evtOriginal = eventRepository.findOne(evt.getId());
		if (evtOriginal != null) {
			evtOriginal.setDetails(evt.getDetails());
			return eventRepository.save(evtOriginal);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEvent(Long eventId) {
		Event evt = eventRepository.findOne(eventId);
		if (evt != null) {
			logger.error("deleteEvent evetID : " + evt.getId());
			List<Customer> customerList = customerRepository.findByEvents(Arrays.asList(evt));
			logger.error("deleteEvent Num Customers size: " + customerList.size());
			customerList.forEach(customer -> {
				customer.removeEvent(evt);
				customerRepository.save(customer);
			});
			eventRepository.delete(evt.getId());
		}
	}

}
