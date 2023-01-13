package com.sdc.springgroupsiteserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class SpringGroupSiteServerApplication {

    @Autowired
    private ProjectRepository repo;
    public static void main(String[] args) {
        SpringApplication.run(SpringGroupSiteServerApplication.class, args);
    }

    @GetMapping("/projects")
    public List<Project> getProjects(){
        return repo.findAll();
    }

    @GetMapping("/projects/{id}")
    public Optional<Project> getProject(@PathVariable Integer id){
        return repo.findById(id);
    }
}
