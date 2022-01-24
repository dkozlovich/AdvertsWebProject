package com.epam.project.dao.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.dao.SectionDAO;
import com.epam.project.dto.SectionCreateDTO;
import com.epam.project.dto.SectionUpdateDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.Section;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionDAOImpl implements SectionDAO {

    private static final String CREATE_SECTION = "INSERT INTO project.sections (name) values(?)";

    private static final String DELETE_SECTION = "DELETE FROM project.sections WHERE id=?";

    private static final String UPDATE_SECTION = "UPDATE project.sections SET name=? WHERE id=?";

    private static final String GET_ALL_SECTIONS = "SELECT * FROM project.sections";

    private static final String GET_BY_ID = "SELECT id, name FROM project.sections WHERE id=?";

    private static final String GET_BY_NAME = "SELECT * FROM project.sections WHERE name=?";

    private static SectionDAO instance;

    private SectionDAOImpl() {

    }

    public static SectionDAO getInstance() {
        if (instance == null) {
            instance = new SectionDAOImpl();
        }
        return instance;
    }

    @Override
    public Optional<Section> getById(int id) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_ID);){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return Optional.of(mapSection(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return Optional.empty();
    }

    @Override
    public Section create(SectionCreateDTO dto) throws DAOException {
        int id = 0;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_SECTION, Statement.RETURN_GENERATED_KEYS);){
            stmt.setString(1, dto.getName());
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
            Section section = new Section();
            section.setId(id);
            section.setName(dto.getName());
            return section;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_SECTION);){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(SectionUpdateDTO dto) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_SECTION);){
            stmt.setString(1, dto.getName());
            stmt.setInt(2, dto.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Section> getAll() throws DAOException {
        List<Section> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_SECTIONS);){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapSection(rs));
            }
        } catch  (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public Optional<Section> getByName(String name) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_NAME);){
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? Optional.of(mapSection(rs)) : Optional.empty();
            } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    private Section mapSection(ResultSet resultSet) throws SQLException {
        Section section = new Section();
        section.setId(resultSet.getInt(1));
        section.setName(resultSet.getString(2));
        return section;
    }
}
