package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.AdvertDAO;
import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.BadRequestServiceException;
import com.epam.project.exception.DAOException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Advert;
import com.epam.project.service.AdvertService;
import com.epam.project.service.SectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class AdvertServiceImpl implements AdvertService {

    private static final Logger LOGGER = LogManager.getLogger();

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

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    @Override
    public Advert createAdvert(AdvertCreateDTO dto) throws ServiceException {
        Advert advert;
        try {
            if (dto.getName() != null && !dto.getName().isEmpty() && dto.getName().length() < 46
                    && dto.getContent() != null && !dto.getContent().isEmpty()
                    && sectionService.getById(dto.getSectionId()).isPresent()
                    && dto.getCost() >= 0) {
                advert = advertDAO.create(dto);
                return advert;
            } else {
                LOGGER.error("Incorrect data.");
                throw new BadRequestServiceException("Incorrect data.");
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
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
            if (dto.getName() != null && dto.getName().length() < 46 && dto.getContent() != null && dto.getCost() > 0) {
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
    public List<Advert> getBySectionId(int sectionId, int offset, int limit, String sortType) throws ServiceException {
        try {
            return advertDAO.getBySectionId(sectionId, offset, limit, sortType);
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

    @Override
    public List<Advert> search(String key, String dateFrom, String dateTo, String sectionId) throws ServiceException {
        try {
            return advertDAO.search(key, dateFrom, dateTo, sectionId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
