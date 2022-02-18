package com.epam.project.service;

import com.epam.project.dto.SectionCreateDTO;
import com.epam.project.dto.SectionUpdateDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    Section createSection(SectionCreateDTO dto) throws ServiceException;
    Optional<Section> getById(int id) throws ServiceException;
    void deleteSection(int id) throws ServiceException;
    void update(SectionUpdateDTO dto) throws ServiceException;
    List<Section> getAll() throws ServiceException;
}
