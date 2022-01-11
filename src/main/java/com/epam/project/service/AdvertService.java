package com.epam.project.service;

import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Advert;

import java.util.List;

public interface AdvertService {
    Advert createAdvert(AdvertCreateDTO dto) throws ServiceException;
    void deleteAdvert(int id) throws ServiceException;
    void updateAdvert(AdvertUpdateDTO dto) throws ServiceException;
    List<Advert> getAll() throws ServiceException;
    List<Advert> getBySectionId(int sectionId) throws ServiceException;
    Advert getById(int id) throws ServiceException;
    List<Advert> getAllByUser(int id) throws ServiceException;
}
