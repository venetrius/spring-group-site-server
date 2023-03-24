package com.sdc.springgroupsiteserver.controller;

import com.sdc.springgroupsiteserver.dto.EventDto;
import com.sdc.springgroupsiteserver.dto.ProjectDto;
import com.sdc.springgroupsiteserver.dto.UserDTO;
import com.sdc.springgroupsiteserver.entities.Event;
import com.sdc.springgroupsiteserver.entities.User;
import com.sdc.springgroupsiteserver.repositories.EventRepository;
import com.sdc.springgroupsiteserver.service.interfaces.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    private final EventRepository eventRepository;

    @PostMapping
    public EventDto postEvent(@RequestBody Event event) {
        EventDto res = new EventDto(eventService.createEvent(event));
        return res;
    }

    @GetMapping
    public List<EventDto> getEvents(){
        return eventService.listEvents()
                .stream()
                .map(event ->new EventDto(event))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable Integer id){
        Event event = eventService.getEvent(id);
        return new EventDto(event);
    }

    @PostMapping("/{id}/register")
    public ResponseEntity registerToEvent(@PathVariable Integer id) {
        eventService.registerAttendee(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @GetMapping("/{id}/attendees")
    public List<UserDTO> getAttendees(@PathVariable Integer id){
        Event event = eventService.getEvent(id);
        return event.getAttendees()
                .stream()
                .map(attendee ->new UserDTO(attendee))
                .collect(Collectors.toList());
    }

    @PostMapping("/{eventId}/registerProject/{projectId}")
    public ResponseEntity registerProject(@PathVariable Integer eventId, @PathVariable Integer projectId) {
        eventService.registerProject(eventId, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
