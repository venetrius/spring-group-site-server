package com.sdc.springgroupsiteserver.entities;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private User admin;

    @Column(name="admin_id", insertable = false, updatable = false)
    private int adminId;

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
