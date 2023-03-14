package com.sdc.springgroupsiteserver;

import com.sdc.springgroupsiteserver.entities.Comment;
import com.sdc.springgroupsiteserver.entities.Project;
import com.sdc.springgroupsiteserver.repositories.CommentRepository;
import com.sdc.springgroupsiteserver.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class SpringGroupSiteServerApplication {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringGroupSiteServerApplication.class, args);
    }

    @GetMapping("/projects")
    public List<Project> getProjects(){
        return repo.findAll();
    }

    @PostMapping("/projects")
    public Project postProject(@RequestBody Project newProject) {
        return repo.save(newProject);
    }
    
    @GetMapping("/projects/{id}")
    public Optional<Project> getProject(@PathVariable Integer id){
        return repo.findById(id);
    }


    @GetMapping("/projects/{id}/comments")
    public List<Comment> getProjectComments(){
        return commentRepository.findAll();
    }

    @PostMapping("/projects/{projectId}/comments")
    public Comment postProjectComment(@PathVariable Integer projectId, @RequestBody Comment newComment) {
        Optional<Project> project = repo.findById(projectId);
        if(project.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Project not found"
            );
        }
        newComment.setProject(project.get());

        return commentRepository.save(newComment);
    }
}
