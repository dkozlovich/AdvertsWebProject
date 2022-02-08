package com.epam.project.dao;

import com.epam.project.exception.DAOException;
import com.epam.project.model.Image;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageDAO {

    Image add(BufferedImage advertImage, int advertId) throws DAOException;

    List<Image> getByAdvertId(int advertId) throws DAOException;

    void delete(int id) throws DAOException;

}
