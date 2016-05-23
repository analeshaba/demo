package com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
