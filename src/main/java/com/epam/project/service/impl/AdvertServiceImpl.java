package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.AdvertDAO;
import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Advert;
import com.epam.project.service.AdvertService;

import java.util.List;

public class AdvertServiceImpl implements AdvertService {

    private static AdvertService instance;

    private AdvertServiceImpl() {

    }

    public static AdvertService getInstance() {
        if (instance == null) {
            instance = new AdvertServiceImpl();
        }
        return instance;
    }

    private AdvertDAO advertDAO = InstanceProvider.getAdvertDAOImpl();

    @Override
    public Advert createAdvert(AdvertCreateDTO dto) throws ServiceException {
        Advert advert = new Advert();
        try {
            if (dto.getName() != null && dto.getName().length() < 46 && dto.getContent() != null && dto.getContent().length() < 46 && dto.getCost() > 0) {
                advert = advertDAO.create(dto);
                return advert;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return advert;
    }

    @Override
    public void deleteAdvert(int id) throws ServiceException {
        try {
            advertDAO.getById(id).orElseThrow(ServiceException::new);
            advertDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateAdvert(AdvertUpdateDTO dto) throws ServiceException {
        try {
            if (dto.getName() != null && dto.getName().length() < 46 && dto.getContent() != null && dto.getContent().length() < 46 && dto.getCost() > 0) {
                advertDAO.update(dto);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advert> getAll() throws ServiceException {
        try {
            return advertDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advert> getBySectionId(int sectionId) throws ServiceException {
        try {
            return advertDAO.getBySectionId(sectionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Advert getById(int id) throws ServiceException {
        try {
            return advertDAO.getById(id).get();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Advert> getAllByUser(int id) throws ServiceException {
        try {
            return advertDAO.getAllByUser(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
