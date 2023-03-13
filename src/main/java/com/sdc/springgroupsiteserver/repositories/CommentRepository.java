package com.sdc.springgroupsiteserver.repositories;


import com.sdc.springgroupsiteserver.entities.Comment;
import com.sdc.springgroupsiteserver.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findById(int id);

    List<Comment> findAll();
}
