package com.sdc.springgroupsiteserver.service.interfaces;

import com.sdc.springgroupsiteserver.entities.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    public Event createEvent(Event event);

    public Event getEvent(int eventId);

    public List<Event> listEvents();

    public boolean registerAttendee(int id);
}
