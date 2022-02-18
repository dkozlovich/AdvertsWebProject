package com.epam.project.util;

import com.epam.project.InstanceProvider;
import com.epam.project.dto.ImageDTO;
import com.epam.project.dto.SectionDTO;
import com.epam.project.dto.UserDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Image;
import com.epam.project.model.Section;
import com.epam.project.model.User;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

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
                .setTotalAdvertsOfSectionNumber(InstanceProvider.getAdvertServiceImpl().getTotalAdvertsOfSectionNumber(entity.getId()));
    }
    public static ImageDTO mapImage(Image image) throws ServiceException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image.getAdvertImage(), "jpg", output);
        String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
        return new ImageDTO()
                .setId(image.getId()).setAdvertImage(imageAsBase64);
    }
}
