package com.sdc.springgroupsiteserver.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter @Setter @NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;

    private String comment;
    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Comment(int id, String comment, Project project){
        this.id = id;
        this.comment = comment;
        this.project = project;
    }
}
