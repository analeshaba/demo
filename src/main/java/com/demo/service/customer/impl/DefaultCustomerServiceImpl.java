package com.demo.service.customer.impl;

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
import com.demo.service.customer.CustomerService;
import com.demo.service.event.impl.DefaultEventServiceImpl;

@Service("customerService")
@Transactional(readOnly = true)
public class DefaultCustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultCustomerServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	EventRepository eventRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findOne(id);
	}
	

	@Override
	public List<Customer> getAll() {
		return  (List<Customer>) customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Customer updateCustomer(Customer customer) {
		Customer original = customerRepository.findOne(customer.getId());
		if (original != null) {
			original.setName(customer.getName());
			return customerRepository.save(original);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteCustomer(Long id) {
		Customer customer = customerRepository.findOne(id);
		if (customer != null) {
			customerRepository.delete(customer.getId());
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Customer registerEvent(Long customerId, Long eventId) {
		logger.info("registeringEvents cust Id ={}, evetId={}", customerId, eventId);
		Customer customer = customerRepository.findOne(customerId);
		Event evt = eventRepository.findOne(eventId);
		if(null != customer  && null != evt){
		 	customer.addEvent(evt);
			return customerRepository.save(customer);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Customer unRegisterEvent(Long customerId, Long eventId) {
		logger.info("UnregisteringEvents cust Id ={}, evetId={}", customerId, eventId);
		Customer customer = customerRepository.findOne(customerId);
		Event evt = eventRepository.findOne(eventId);
		if(null != customer  && null != evt){
		 	customer.removeEvent(evt);
			return customerRepository.save(customer);
		}
		return null;
	}	
	
}
