package com.sdc.springgroupsiteserver;

import com.sdc.springgroupsiteserver.dto.ProjectDto;
import com.sdc.springgroupsiteserver.entities.Comment;
import com.sdc.springgroupsiteserver.entities.Project;
import com.sdc.springgroupsiteserver.entities.User;
import com.sdc.springgroupsiteserver.repositories.CommentRepository;
import com.sdc.springgroupsiteserver.repositories.ProjectRepository;
import com.sdc.springgroupsiteserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class SpringGroupSiteServerApplication {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringGroupSiteServerApplication.class, args);
    }

    @GetMapping("/projects")
    public List<ProjectDto> getProjects(){
        return repo.findAll()
                .stream()
                .map(project ->new ProjectDto(project))
                .collect(Collectors.toList());
    }

    @PostMapping("/projects")
    public ProjectDto postProject(@RequestBody Project newProject) {
        User currrentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        newProject.setAdmin(currrentUser);
        return new ProjectDto(repo.save(newProject));
    }
    
    @GetMapping("/projects/{id}")
    public ProjectDto getProject(@PathVariable Integer id){
        Project project = repo.findById(id).get();
        if(project == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found");
        }
        return new ProjectDto(project);
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
