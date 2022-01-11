package com.epam.project.dao;

import com.epam.project.dto.UserDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.User;

import java.util.List;

public interface UserDAO {
    List<User> searchByUsername(String username) throws DAOException;
    List<User> getAllUsers() throws DAOException;
    User getByUsername(String username) throws DAOException;
    User getById(int id) throws DAOException;
    User createUser(UserDTO dto) throws DAOException;
}
