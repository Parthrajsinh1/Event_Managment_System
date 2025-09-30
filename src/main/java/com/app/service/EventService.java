package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import com.app.entities.Event;
import com.app.entities.User;

public interface EventService {

	Optional<Event> getEventByDate(LocalDate date);

	public Optional<Event> getEventById(Long id);

	public List<Event> getAllEvents();

	public Event createEvent(String email , String password , String name , LocalDate date);

	Event updateEvent(Long id, String email, String password, String name, LocalDate date);

	String deleteEvent(Long id , String email, String password);


}
