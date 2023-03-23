package com.sdc.springgroupsiteserver.service.impl;

import com.sdc.springgroupsiteserver.entities.Event;
import com.sdc.springgroupsiteserver.entities.User;
import com.sdc.springgroupsiteserver.repositories.EventRepository;
import com.sdc.springgroupsiteserver.repositories.UserRepository;
import com.sdc.springgroupsiteserver.service.interfaces.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Event createEvent(Event event) {
        if(!validateEvent(event)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid event");
        }
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(int eventId) {
        Event event = eventRepository.findById(eventId);
        if(event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found");
        }
        return event;
    }

    @Override
    public List<Event> listEvents() {
        return eventRepository.findAll();
    }

    @Override
    public boolean registerAttendee(int id) {
        Event event = getEvent(id);
        if(event.getEndDate().before(new Date())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can only sign up for future events");
        }
        User currrentUser = userRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).get();

        if(!event.getAttendees().contains(currrentUser)) {
            event.getAttendees().add(currrentUser);
            eventRepository.save(event);
        }
        return true;
    }

    private boolean validateEvent(Event event) {
        return validateTime(event);
    }

    private boolean validateTime(Event event) {
        if(event.getStartDate() != null && event.getEndDate() != null) {
            return  event.getStartDate().before(event.getEndDate());
        }
        return true;
    }
}
