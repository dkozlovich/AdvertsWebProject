package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.AdvertDAO;
import com.epam.project.dao.SectionDAO;
import com.epam.project.dto.SectionCreateDTO;
import com.epam.project.dto.SectionUpdateDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.exception.EntityAlreadyExistException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Section;
import com.epam.project.service.SectionService;

import java.util.List;
import java.util.Optional;

public class SectionServiceImpl implements SectionService {

    private static SectionService instance;

    private SectionServiceImpl() {

    }

    public static SectionService getInstance() {
        if (instance == null) {
            instance = new SectionServiceImpl();
        }
        return instance;
    }

    private SectionDAO sectionDAO = InstanceProvider.getSectionDAOImpl();

    @Override
    public Section createSection(SectionCreateDTO dto) throws ServiceException {
        try {
            Optional<Section> section = sectionDAO.getByName(dto.getName());
            if (section.isPresent()) {
                throw new EntityAlreadyExistException();
            } else {
                if (dto.getName() != null && dto.getName().length() < 46) {
                    return sectionDAO.create(dto);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public void deleteSection(int id) throws ServiceException {
        try {
            sectionDAO.getById(id).orElseThrow(ServiceException::new);
            sectionDAO.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(SectionUpdateDTO dto) throws ServiceException {
        try {
            sectionDAO.update(dto);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Section> getAll() throws ServiceException {
        try {
            return sectionDAO.getAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Section> getById(int id) throws ServiceException {
        try {
            return sectionDAO.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
