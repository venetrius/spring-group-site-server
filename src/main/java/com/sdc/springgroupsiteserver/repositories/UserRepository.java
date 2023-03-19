package com.sdc.springgroupsiteserver.repositories;

import com.sdc.springgroupsiteserver.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);

    Optional<User> findByEmail(String email);
}
