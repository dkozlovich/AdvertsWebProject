package com.epam.project.service;

import com.epam.project.dto.UserDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    User createUser(UserDTO dto) throws ServiceException;
    void deleteUser(int id) throws ServiceException;
    User getById(int id) throws ServiceException;
    boolean checkLogin(String enterLogin, String enterPass) throws ServiceException;
    void loginUser(HttpServletRequest request, String login, String password) throws ServiceException;
    void setSessionAttributes(HttpServletRequest request) throws ServiceException;
    List<UserDTO> getAllUser() throws ServiceException;
}
