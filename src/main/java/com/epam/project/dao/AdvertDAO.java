package com.epam.project.dao;

import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.Advert;

import java.util.List;
import java.util.Optional;

public interface AdvertDAO {
    List<Advert> getAll() throws DAOException;

    Optional<Advert> getById(int id) throws DAOException;

    List<Advert> getBySectionId(int id, int offset, int limit, String sortType) throws DAOException;

    Advert create(AdvertCreateDTO advert) throws DAOException;

    void delete(int id) throws DAOException;

    void update(AdvertUpdateDTO dto) throws DAOException;

    List<Advert> getAllByUser(int userId) throws DAOException;

    List<Advert> search(String key, String dateFrom, String dateTo, String sectionId, int offset, int limit) throws DAOException;

    void updateAdvertDate(int advertId) throws DAOException;

    int getTotalAdvertsOfSectionNumber(int sectionId) throws DAOException;

}
