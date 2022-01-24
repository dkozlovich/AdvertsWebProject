package com.epam.project.dao.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.dao.MessageDAO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private static MessageDAO instance;

    private MessageDAOImpl() {

    }

    public static MessageDAO getInstance() {
        if (instance == null) {
            instance = new MessageDAOImpl();
        }
        return instance;
    }

    private static final String CREATE_MESSAGE = "INSERT INTO project.messages (advertID, author, content, created) values (?,?,?,?)";

    private static final String GET_BY_ADVERTID = "SELECT * FROM project.messages WHERE advertID=?";

    @Override
    public Message create(String content, String author, int advertId) throws DAOException {
        Message message = new Message();
        int id = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_MESSAGE, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, advertId);
            stmt.setString(2, author);
            stmt.setString(3, content);
            stmt.setTimestamp(4, timestamp);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            message.setId(id);
            message.setAdvertId(advertId);
            message.setAuthor(author);
            message.setContent(content);
            message.setCreated(timestamp);
            return message;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Message> getByAdvertId(int advertId) throws DAOException {
        List<Message> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_ADVERTID)) {
            stmt.setInt(1, advertId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt(1));
                message.setAdvertId(rs.getInt(2));
                message.setAuthor(rs.getString(3));
                message.setContent(rs.getString(4));
                message.setCreated(rs.getTimestamp(5));
                list.add(message);
            }
            return list;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
