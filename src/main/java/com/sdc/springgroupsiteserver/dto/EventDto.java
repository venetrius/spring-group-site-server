package com.sdc.springgroupsiteserver.dto;

import com.sdc.springgroupsiteserver.entities.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class EventDto {
    private int id;
    private String name;
    private String description;
    private String location;
    private Timestamp startDate;
    private  Timestamp endDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public EventDto(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
    }
}
