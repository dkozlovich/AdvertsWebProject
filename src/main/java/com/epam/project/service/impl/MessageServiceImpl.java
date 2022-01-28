package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.MessageDAO;
import com.epam.project.dao.UserDAO;
import com.epam.project.dto.MessageDTO;
import com.epam.project.dto.UserDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Message;
import com.epam.project.model.User;
import com.epam.project.service.MessageService;
import com.epam.project.util.DTOMapper;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    private static MessageService instance;

    private MessageDAO messageDAO = InstanceProvider.getMessageDAOImpl();

    private UserDAO userDAO = InstanceProvider.getUserDAOImpl();

    private MessageServiceImpl() {

    }

    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageServiceImpl();
        }
        return instance;
    }

    @Override
    public Message createMessage(String content, int userId, int advertId) throws ServiceException {
        Message message = new Message();
        try {
            if (content != null) {
                message = messageDAO.create(content,userId,advertId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return message;
    }

    @Override
    public List<MessageDTO> findByAdvertId(int advertId, int offset, int limit) throws ServiceException {
        try {
            List<MessageDTO> result = new ArrayList<>();
            List<Message> messages = messageDAO.getByAdvertId(advertId, offset, limit);
            for (Message entity : messages) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setId(entity.getId());
                messageDTO.setAdvertId(entity.getAdvertId());
                User user = userDAO.getById(entity.getUserId());
                UserDTO userDTO = DTOMapper.mapUser(user);
                messageDTO.setUserDTO(userDTO);
                messageDTO.setContent(entity.getContent());
                messageDTO.setCreated(entity.getCreated());
                result.add(messageDTO);
            }
            return result;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getTotalMessagesNumber(int advertId) throws ServiceException {
        try {
            return messageDAO.findTotalMessagesNumber(advertId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
