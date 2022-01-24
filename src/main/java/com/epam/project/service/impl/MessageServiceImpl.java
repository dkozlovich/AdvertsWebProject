package com.epam.project.service.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.dao.MessageDAO;
import com.epam.project.exception.DAOException;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Message;
import com.epam.project.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private static MessageService instance;

    private MessageServiceImpl() {

    }

    public static MessageService getInstance() {
        if (instance == null) {
            instance = new MessageServiceImpl();
        }
        return instance;
    }

    private MessageDAO messageDAO = InstanceProvider.getMessageDAOImpl();

    @Override
    public Message createMessage(String content, String author, int advertId) throws ServiceException {
        Message message = new Message();
        try {
            if (content != null && author != null && author.length() < 46) {
                message = messageDAO.create(content,author,advertId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return message;
    }

    @Override
    public List<Message> getByAdvertId(int advertId) throws ServiceException {
        try {
            return messageDAO.getByAdvertId(advertId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
