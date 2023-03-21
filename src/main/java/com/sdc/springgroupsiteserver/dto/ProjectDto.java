package com.sdc.springgroupsiteserver.dto;

import com.sdc.springgroupsiteserver.entities.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ProjectDto {

    private int id;
    private Integer adminId;
    private String name;
    private String description;
    private String summary;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.adminId = project.getAdminId();
        if(adminId == 0 && project.getAdmin() != null) {
            this.adminId = project.getAdmin().getId();
        }
        this.description = project.getDescription();
        this.summary = project.getSummary();
        this.createdAt = project.getCreatedAt();
        this.updatedAt = project.getUpdatedAt();
    }
}
