package com.sdc.springgroupsiteserver.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "projects")
@Getter @Setter @NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private String description;
    private String summary;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    public Project(int id, String name, String description, String summary) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.summary = summary;
    }
}
