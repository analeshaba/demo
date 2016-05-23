package com.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.domain.Customer;
import com.demo.domain.Event;
import com.demo.repository.CustomerRepository;
import com.demo.repository.EventRepository;

@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadDummyEventData(EventRepository repository) {
		return (args) -> {
			Event evt = new Event(1, "Sample Dummy Event 1");
			repository.save(evt);
	 
		};
	}

	@Bean
	public CommandLineRunner loadDummyCustomerData(CustomerRepository repository) {
		return (args) -> {
			Customer customer = new Customer(1, "Dummy Customer 1");
			repository.save(customer);
		};

	}
}
