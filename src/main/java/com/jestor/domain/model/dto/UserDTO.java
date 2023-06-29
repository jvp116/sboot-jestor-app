package com.jestor.domain.model.dto;

import com.jestor.domain.model.user.Role;
import com.jestor.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private Role role;

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
}
