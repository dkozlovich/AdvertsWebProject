package com.epam.project.service;

import com.epam.project.dto.MessageDTO;
import com.epam.project.exception.ServiceException;
import com.epam.project.model.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(String content, int userId, int advertId) throws ServiceException;
    List<Message> getByAdvertId(int advertId) throws ServiceException;
    List<MessageDTO> findByAdvertId(int advertId) throws ServiceException;
}
