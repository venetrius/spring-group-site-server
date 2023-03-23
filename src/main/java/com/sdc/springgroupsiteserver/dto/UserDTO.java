package com.sdc.springgroupsiteserver.dto;

import com.sdc.springgroupsiteserver.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;

    private String userName;
    private String displayName;

    private String email;
    private String role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.role = user.getRole().name();
    }
}
