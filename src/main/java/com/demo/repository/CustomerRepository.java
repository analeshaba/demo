package com.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.Customer;
import com.demo.domain.Event;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByEvents(Collection<Event> event);
 
}
