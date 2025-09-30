package com.app.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Event;
import com.app.entities.User;

public interface EventDao extends JpaRepository<Event, Long> {
	Optional<Event> findByDate(LocalDate date);


}
