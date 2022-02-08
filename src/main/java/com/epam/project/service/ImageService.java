package com.epam.project.service;

import com.epam.project.exception.ServiceException;
import com.epam.project.model.Image;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageService {
    Image add(BufferedImage advertImage, int advertId) throws ServiceException;

    List<Image> getByAdvertId(int advertId) throws ServiceException;

    void delete(int id) throws ServiceException;
}
