package com.demo.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Customer;
import com.demo.domain.Event;
import com.demo.service.customer.CustomerService;
import com.demo.service.event.EventService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.common.collect.Iterables;

/**
 * Handles web request and returns json data
 *
 */
@RestController
public class ApplicationController {

	public static final String README_MSG = "Please see the documentation on the valid endpoints";

	@Autowired
	EventService eventService;

	@Autowired
	CustomerService customerService;

	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	@RequestMapping("/")
	public String index() {
		return README_MSG;
	}


	@RequestMapping(path = "/customer", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomers(
			@RequestParam(value = "q", defaultValue = "") String qryString) {

		log.debug("getAllCustomer:: q={}", qryString);
		List<Customer> customerList = customerService.getAll();
		if (Iterables.size(customerList) == 0) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") String customerId) {
		log.debug("getCustomer:: id={}", customerId);
		if (StringUtils.isNumber(customerId)) {
			Customer cust = customerService.getCustomer(Long.valueOf(customerId));
			if (cust != null) {
				return new ResponseEntity<Customer>(cust, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	

	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody(required = true) Customer cust) {
		log.debug("createCustomer:: data={}", cust);
		cust = customerService.createCustomer(cust);
		if (null != cust) {
			return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") String customerId) {
		log.debug("deleteCustomer:: id={}", customerId);
		if (StringUtils.isNumber(customerId)) {
			customerService.deleteCustomer(Long.valueOf(customerId));
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/customer", method = { RequestMethod.PATCH,
			RequestMethod.PUT }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(@RequestBody(required = true) Customer cust) {
		log.debug("updateCustomer:: data={}", cust);
		Customer custUpdated = customerService.updateCustomer(cust);
		if (custUpdated != null) {
			return new ResponseEntity<Customer>(custUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@RequestMapping(path = "/customer/{customerId}/event/{eventId}", method = RequestMethod.POST)
	public ResponseEntity<Customer> customerRegisterEvent(@PathVariable(value = "customerId") String customerId,
			@PathVariable(value = "eventId") String eventId) {
		log.debug("customerRegisterEvent:: customerId={}, eventId={}", customerId, eventId);
		if (StringUtils.isNumber(customerId) && StringUtils.isNumber(eventId)) {
			Customer cust = customerService.registerEvent(Long.valueOf(customerId), Long.valueOf(eventId));
			if (null != cust) {
				return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(path = "/customer/{customerId}/event/{eventId}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> customerUnRegisterEvent(@PathVariable(value = "customerId") String customerId,
			@PathVariable(value = "eventId") String eventId) {
		log.debug("customerRegisterEvent:: customerId={}, eventId={}", customerId, eventId);
		if (StringUtils.isNumber(customerId) && StringUtils.isNumber(eventId)) {
			Customer cust = customerService.unRegisterEvent(Long.valueOf(customerId), Long.valueOf(eventId));
			if (null != cust) {
				return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

	}
	
	@RequestMapping(path = "/event", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEvents(@RequestParam(value = "q", defaultValue = "") String qryString) {

		log.debug("getAllEvents:: q={}", qryString);
		List<Event> eventList = eventService.getAll();
		if (Iterables.size(eventList) == 0) {
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}

	@RequestMapping(path = "/event/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable(value = "id") String eventId) {
		log.debug("getEvent:: id={}", eventId);
		if (StringUtils.isNumber(eventId)) {
			Event evt = eventService.getEvent(Long.valueOf(eventId));
			if (evt != null) {
				return new ResponseEntity<Event>(evt, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/event", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> createEvent(@RequestBody(required = true) Event evt) {
		log.debug("createEvent:: data={}", evt);
		evt = eventService.createEvent(evt);
		if (null != evt) {
			return new ResponseEntity<Event>(evt, HttpStatus.CREATED);
		}
		return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "/event/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") String eventId) {
		log.debug("deleteEvent:: id={}", eventId);
		if (StringUtils.isNumber(eventId)) {
			eventService.deleteEvent(Long.valueOf(eventId));
			return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/event", method = { RequestMethod.PATCH,
			RequestMethod.PUT }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> updateEvent(@RequestBody(required = true) Event evt) {
		log.debug("updateEvent:: data={}", evt);
		Event evtUpdated = eventService.updateEvent(evt);
		if (evtUpdated != null) {
			return new ResponseEntity<Event>(evtUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
	}
}
