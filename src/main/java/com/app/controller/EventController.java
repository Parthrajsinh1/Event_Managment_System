package com.app.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.EventRequest;
import com.app.entities.Event;
import com.app.entities.User;
import com.app.exception.ResourceNotFoundException;
import com.app.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

    public EventController() {
        System.out.println("in event controller " + getClass());
    }

    @Autowired
    EventService eventService;

    @GetMapping("/allEvents")
    public ResponseEntity<?> getAllEvents() {
        try {
            return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch events", e);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }
        return ResponseEntity.ok(event.get());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getEventByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Optional<Event> event = eventService.getEventByDate(date);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException("Event on date " + date + " not found");
        }
        return ResponseEntity.ok(event.get());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody EventRequest eventRequest) {
        Event eventSave = eventService.createEvent(eventRequest.getEmail(), eventRequest.getPassword(),
                eventRequest.getName(), eventRequest.getDate());

        if (eventSave == null) {
            throw new IllegalArgumentException("Only ADMIN can create event!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Event " + eventSave.getEventName() + " Created!!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest) {
        Event eventUpdate = eventService.updateEvent(id, eventRequest.getEmail(), eventRequest.getPassword(),
                eventRequest.getName(), eventRequest.getDate());

        if (eventUpdate == null) {
            throw new IllegalArgumentException("Only ADMIN can update the event!");
        }

        return ResponseEntity.ok("Event updated successfully!!");
    }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long id, @RequestParam String email,
			@RequestParam String password) {

		String response = eventService.deleteEvent(id, email, password);

		if (response.equals("Only ADMIN can delete events!"))
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		else if (response.equals("Event not found!"))
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		return ResponseEntity.ok(response);
	}
}
