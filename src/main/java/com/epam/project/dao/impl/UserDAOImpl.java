package com.epam.project.dao.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.dao.UserDAO;
import com.epam.project.dto.UserDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String GET_BY_ID = "SELECT id, username, userpassword, isadmin FROM project.users WHERE id=?";

    private static final String SEARCH_BY_USERNAME = "SELECT * FROM project.users WHERE username LIKE ?";

    private static final String GET_ALL_USERS = "SELECT * FROM project.users";

    private static final String CREATE_USER = "INSERT INTO project.users (username,userpassword) values(?,?)";

    private static final String GET_BY_USERNAME = "SELECT * FROM project.users WHERE username=?";

    private static UserDAO instance;

    private UserDAOImpl() {

    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public User getById(int id) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_ID);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? mapUser(rs) : null;
        } catch (Exception e ) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> searchByUsername(String username) throws DAOException {
        List<User> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SEARCH_BY_USERNAME);){
            stmt.setString(1, "%" + username + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL_USERS);){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapUser(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public User createUser(UserDTO dto) throws DAOException {
        int id = 0;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);){
            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getPassword());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
            User user = new User();
            user.setId(id);
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            return user;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User getByUsername(String username) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_USERNAME);){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? mapUser(rs) : null;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setAdmin(resultSet.getBoolean(4));
        return user;
    }
}
