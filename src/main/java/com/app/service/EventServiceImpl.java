package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.app.dao.EventDao;
import com.app.dao.UserDao;
import com.app.entities.Event;
import com.app.entities.User;
import com.app.entities.UserRole;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	public EventServiceImpl() {
		System.out.println("in event service " + getClass());
	}

	@Autowired
	EventDao eventDao;
	
	@Autowired
	UserDao userDao; //

	@Override
	public List<Event> getAllEvents() {
		return eventDao.findAll();
	}

	@Override
	public Optional<Event> getEventById(Long id) {
		return eventDao.findById(id);
	}

	@Override
	public Optional<Event> getEventByDate(LocalDate date) {
		return eventDao.findByDate(date);
	}

	@Override
	public Event createEvent(String email, String password , String name , LocalDate date) {
		User user = userDao.findByEmail(email);
		
		if(user==null || !user.getPassword().equals(password) || !user.getRole().getRoleName().equals(UserRole.ADMIN)) {
			return null;
		}
		
		Event event = new Event();
		event.setDate(date);
		event.setEventName(name);
		
		return eventDao.save(event);
	}

	@Override
	public Event updateEvent(Long id, String email, String password, String name, LocalDate date) {
		
		User user = userDao.findByEmail(email);
		
		if( eventDao.findById(id)==null 
				|| !user.getEmail().equals(email) 
				|| !user.getPassword().equals(password) 
				|| !user.getRole().getRoleName().equals(UserRole.ADMIN))
		{
			return null;
		}
		
		Event updatingEvent = eventDao.findById(id).orElse(null);
		
		updatingEvent.setEventName(name);
		updatingEvent.setDate(date);
		
		return eventDao.save(updatingEvent);
	}

	@Override
	public String deleteEvent(Long id, String email, String password) {
		
		User user = userDao.findByEmail(email);
		
		if(user==null || !user.getPassword().equals(password) || !user.getRole().getRoleName().equals(UserRole.ADMIN)) {
			return "only Admin can delete event !!";
		}
		
		Optional<Event> eventOpt = eventDao.findById(id);
		if(eventOpt.isEmpty()) {
			return "event not found !";
		}
		
		eventDao.deleteById(id);
		
		return "event deleted !!";
	}



}

	
	

