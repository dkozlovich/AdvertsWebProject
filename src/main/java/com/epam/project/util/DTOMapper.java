package com.epam.project.util;

import com.epam.project.dto.UserDTO;
import com.epam.project.model.User;

public class DTOMapper {
    public static UserDTO mapUser(User entity) {
        return new UserDTO()
                .setId(entity.getId())
                .setUsername(entity.getUsername())
                .setPassword(entity.getPassword())
                .setAdmin(entity.isAdmin());
    }
}
