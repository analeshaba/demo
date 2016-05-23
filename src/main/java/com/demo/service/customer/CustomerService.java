package com.demo.service.customer;

import java.util.List;

import com.demo.domain.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	Customer getCustomer(Long customerId);
	Customer registerEvent(Long customerId, Long eventId);
	Customer unRegisterEvent(Long customerId, Long eventId);
	List<Customer> getAll();
	Customer updateCustomer(Customer customer);
	void deleteCustomer(Long customerId);
}
