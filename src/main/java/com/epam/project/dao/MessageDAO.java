package com.epam.project.dao;

import com.epam.project.exception.DAOException;
import com.epam.project.model.Message;

import java.util.List;

public interface MessageDAO {
    Message create(String content, int userId, int advertId) throws DAOException;
    List<Message> getByAdvertId(int advertId, int offset, int limit) throws DAOException;
    int findTotalMessagesNumber(int advertId) throws DAOException;
}
