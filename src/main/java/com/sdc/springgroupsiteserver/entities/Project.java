package com.sdc.springgroupsiteserver.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "projects")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;

    private User admin;
    private String name;
    private String description;
    private String summary;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;
}
