package com.sdc.springgroupsiteserver.repositories;

import com.sdc.springgroupsiteserver.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findById(int id);

    List<Event> findAll();
}
