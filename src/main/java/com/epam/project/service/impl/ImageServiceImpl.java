package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.ImageDAO;
import com.epam.project.exception.DAOException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Image;
import com.epam.project.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.BufferedImage;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LogManager.getLogger();

    private ImageDAO imageDAO = InstanceProvider.getImageDAOImpl();

    private static ImageService instance;

    private ImageServiceImpl() {

    }

    public static ImageService getInstance() {
        if (instance == null) {
            instance = new ImageServiceImpl();
        }
        return instance;
    }

    @Override
    public Image add(BufferedImage advertImage, int advertId) throws ServiceException {
        try {
            return imageDAO.add(advertImage, advertId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Image> getByAdvertId(int advertId) throws ServiceException {
        try {
            return imageDAO.getByAdvertId(advertId);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            imageDAO.delete(id);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
