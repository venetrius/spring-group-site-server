package com.sdc.springgroupsiteserver.repositories;


import com.sdc.springgroupsiteserver.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
    Project findById(int id);

    List<Project> findAll();
}
