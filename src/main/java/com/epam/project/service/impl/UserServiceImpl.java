package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.AdvertDAO;
import com.epam.project.dao.SectionDAO;
import com.epam.project.dao.UserDAO;
import com.epam.project.dto.SectionDTO;
import com.epam.project.dto.UserDTO;
import com.epam.project.exception.*;
import com.epam.project.model.Section;
import com.epam.project.model.User;
import com.epam.project.service.SectionService;
import com.epam.project.service.UserService;
import com.epam.project.util.DTOMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static UserService instance;

    private UserDAO userDAO = InstanceProvider.getUserDAOImpl();

    private SectionDAO sectionDAO = InstanceProvider.getSectionDAOImpl();

    private AdvertDAO advertDAO = InstanceProvider.getAdvertDAOImpl();

    private SectionService sectionService = InstanceProvider.getSectionServiceImpl();

    private UserServiceImpl() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User createUser(UserDTO dto) throws ServiceException {
        try {
            User user;
            user = userDAO.getByUsername(dto.getUsername());
            if (user != null) {
                LOGGER.error("User with such name is already exists.");
                throw new EntityAlreadyExistException("User with such name is already exists.");
            } else {
                if (dto.getUsername() != null && !dto.getUsername().isEmpty()
                        && dto.getPassword() != null && !dto.getPassword().isEmpty()
                        && dto.getUsername().length() < 46 && dto.getPassword().length() < 46) {
                    return userDAO.createUser(dto);
                } else {
                    LOGGER.error("Incorrect login or password.");
                    throw new BadRequestServiceException("Incorrect login or password.");
                }
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        try {
            User user = userDAO.getById(id);
            if (user != null) {
                userDAO.deleteUser(id);
            } else {
                LOGGER.error("User is not found.");
                throw new BadRequestServiceException("User is not found.");
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }

    public boolean checkLogin(String enterLogin, String enterPass) throws ServiceException {
        User user;
        try {
            user = userDAO.getByUsername(enterLogin);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
        if (user == null) {
            return false;
        } else {
            return (user.getPassword().equals(enterPass));
        }
    }

    @Override
    public void loginUser(HttpServletRequest request, String login, String password) throws ServiceException {
        UserService userService = getInstance();
        try {
            if (!userService.checkLogin(login, password)) {
                throw new IncorrectLoginOrPassException();
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", userDAO.getByUsername(login));
            }
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException();
        }
    }

    @Override
    public List<UserDTO> getAllUser() throws ServiceException {
        try {
            List<UserDTO> userDTO = new ArrayList<>();
            List<User> allUsers = userDAO.getAllUsers();
            for (User user : allUsers) {
                userDTO.add(mapUserDTO(user));
            }
            return userDTO;
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException();
        }
    }

    private static UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setAdmin(user.isAdmin());
    }

    public void setSessionAttributes(HttpServletRequest request) throws ServiceException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("users", userDAO.getAllUsers());
            List<SectionDTO> sectionsDTO = new ArrayList<>();
            List<Section> sections = sectionService.getAll();
            for (Section section : sections) {
                sectionsDTO.add(DTOMapper.mapSection(section));
            }
            session.setAttribute("sections", sectionsDTO);
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new ServiceException();
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            return userDAO.getById(id);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ServiceException(e);
        }
    }
}
