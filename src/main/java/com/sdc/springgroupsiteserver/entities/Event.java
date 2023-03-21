package com.sdc.springgroupsiteserver.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;

    private String name;
    private String description;
    private String location;
    private Timestamp start;
    private  Timestamp end;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;
}
