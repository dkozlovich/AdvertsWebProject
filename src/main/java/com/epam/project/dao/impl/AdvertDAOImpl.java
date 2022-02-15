package com.epam.project.dao.impl;

import com.epam.project.connection.ConnectionPool;
import com.epam.project.dao.AdvertDAO;
import com.epam.project.dto.AdvertCreateDTO;
import com.epam.project.dto.AdvertUpdateDTO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.Advert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdvertDAOImpl implements AdvertDAO {

    private static final String GET_ALL_ADVERTS = "SELECT * FROM project.adverts";

    private static final String GET_ALL_BY_USER = "SELECT * FROM project.adverts WHERE userID=?";

    private static final String GET_BY_ID = "SELECT * FROM project.adverts WHERE id=?";

    private static final String GET_BY_SECTION_ID = "SELECT * FROM project.adverts WHERE sectionID=? LIMIT ?,?";

    private static final String CREATE_ADVERT = "INSERT INTO project.adverts (sectionID,name,content,cost,created,modified,userID) values (?,?,?,?,?,?,?)";

    private static final String DELETE_ADVERT = "DELETE FROM project.adverts WHERE id=?";

    private static final String UPDATE_ADVERT = "UPDATE project.adverts SET sectionID=?, name=?, content=?, cost=?, modified=? WHERE id=?";

    private static final String SEARCH_ADVERT = "SELECT * FROM project.adverts WHERE content LIKE ?";

    private static final String UPDATE_ADVERT_DATE = "UPDATE project.adverts SET modified=? WHERE id=?";

    private static AdvertDAO instance;

    private AdvertDAOImpl() {

    }

    public static AdvertDAO getInstance() {
        if (instance == null) {
            instance = new AdvertDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Advert> getAll() throws DAOException {
        List<Advert> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL_ADVERTS);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapAdvert(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public Optional<Advert> getById(int id) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_ID);){
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return Optional.of(mapAdvert(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return Optional.empty();
    }

    @Override
    public Advert create(AdvertCreateDTO dto) throws DAOException {
        Advert advert = new Advert();
        int id = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_ADVERT, Statement.RETURN_GENERATED_KEYS);){
            stmt.setInt(1, dto.getSectionId());
            stmt.setString(2,dto.getName());
            stmt.setString(3,dto.getContent());
            stmt.setDouble(4,dto.getCost());
            stmt.setTimestamp(5,timestamp);
            stmt.setTimestamp(6,timestamp);
            stmt.setInt(7,dto.getUserId());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            advert.setId(id);
            advert.setSectionId(dto.getSectionId());
            advert.setName(dto.getName());
            advert.setContent(dto.getContent());
            advert.setCost(dto.getCost());
            advert.setCreated(timestamp);
            return advert;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_ADVERT);){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(AdvertUpdateDTO dto) throws DAOException  {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_ADVERT);){
            stmt.setInt(1,dto.getSectionId());
            stmt.setString(2,dto.getName());
            stmt.setString(3,dto.getContent());
            stmt.setDouble(4,dto.getCost());
            stmt.setTimestamp(5,timestamp);
            stmt.setInt(6,dto.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Advert> getAllByUser(int userId) throws DAOException  {
        List<Advert> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ALL_BY_USER);){
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapAdvert(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public List<Advert> getBySectionId(int sectionId, int offset, int limit) throws DAOException {
        List<Advert> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_SECTION_ID);){
            stmt.setInt(1,sectionId);
            stmt.setInt(2,offset);
            stmt.setInt(3,limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapAdvert(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override
    public List<Advert> search(String key) throws DAOException {
        List<Advert> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SEARCH_ADVERT)) {
            stmt.setString(1, "%" + key + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapAdvert(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }



    public void updateAdvertDate(int advertId) throws DAOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_ADVERT_DATE);) {
            stmt.setTimestamp(1, timestamp);
            stmt.setInt(2, advertId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private Advert mapAdvert(ResultSet resultset) throws SQLException {
        Advert advert = new Advert();
        advert.setId(resultset.getInt(1));
        advert.setSectionId(resultset.getInt(2));
        advert.setName(resultset.getString(3));
        advert.setContent(resultset.getString(4));
        advert.setCost(resultset.getDouble(5));
        advert.setCreated(resultset.getTimestamp(6));
        advert.setModified(resultset.getTimestamp(7));
        advert.setUserId(resultset.getInt(8));
        return advert;
    }
}
