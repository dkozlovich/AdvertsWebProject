package com.epam.project.dao;

import com.epam.project.dto.SectionCreateDTO;
import com.epam.project.dto.SectionUpdateDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionDAO {
    Optional<Section> getById(int id) throws DAOException;
    Optional<Section> getByName(String name) throws DAOException;
    Section create(SectionCreateDTO dto) throws DAOException;
    void delete(int id) throws DAOException;
    void update(SectionUpdateDTO dto) throws DAOException;
    List<Section> getAll() throws DAOException;
}
