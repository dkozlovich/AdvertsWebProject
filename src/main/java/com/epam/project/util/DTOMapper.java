package com.epam.project.util;

import com.epam.project.InstanceProvider;
import com.epam.project.dto.SectionDTO;
import com.epam.project.dto.UserDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Section;
import com.epam.project.model.User;

public class DTOMapper {
    public static UserDTO mapUser(User entity) {
        return new UserDTO()
                .setId(entity.getId())
                .setUsername(entity.getUsername())
                .setPassword(entity.getPassword())
                .setAdmin(entity.isAdmin());
    }
    public static SectionDTO mapSection(Section entity) throws ServiceException {
        return new SectionDTO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setTotalAdvertsOfSectionNumber(InstanceProvider.getSectionServiceImpl().getTotalAdvertsOfSectionNumber(entity.getId()));
    }
}
