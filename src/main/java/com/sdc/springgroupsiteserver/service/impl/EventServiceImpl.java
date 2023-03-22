package com.sdc.springgroupsiteserver.service.impl;

import com.sdc.springgroupsiteserver.entities.Event;
import com.sdc.springgroupsiteserver.repositories.EventRepository;
import com.sdc.springgroupsiteserver.service.interfaces.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Override
    public Event createEvent(Event event) {
        if(!validateEvent(event)) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid event");
        }
        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(int eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public List<Event> listEvents() {
        return eventRepository.findAll();
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
