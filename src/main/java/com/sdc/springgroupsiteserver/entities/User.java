package com.sdc.springgroupsiteserver.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int id;
    
    private String userName;
    private String displayName;

    @Getter(AccessLevel.NONE)
    private String token;

    public User(int id, String userName, String displayName, String token){
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.token = token;
    }
}
