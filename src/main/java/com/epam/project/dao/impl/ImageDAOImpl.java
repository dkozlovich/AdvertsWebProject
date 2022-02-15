package com.epam.project.dao.impl;

import com.epam.project.InstanceProvider;
import com.epam.project.connection.ConnectionPool;
import com.epam.project.dao.AdvertDAO;
import com.epam.project.dao.ImageDAO;
import com.epam.project.exception.DAOException;
import com.epam.project.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDAOImpl implements ImageDAO {

    private static ImageDAO instance;

    private ImageDAOImpl() {

    }

    public static ImageDAO getInstance() {
        if (instance == null) {
            instance = new ImageDAOImpl();
        }
        return instance;
    }

    AdvertDAO advertDAO = InstanceProvider.getAdvertDAOImpl();

    private static final String ADD_IMAGE = "INSERT INTO project.images (advertImage, advertID) values (?,?)";

    private static final String GET_BY_ADVERT_ID = "SELECT * FROM project.images WHERE advertID=?";

    private static final String DELETE_IMAGE = "DELETE FROM project.images WHERE id=?";

    private static final String GET_ADVERT_ID_BY_IMAGE_ID = "SELECT (advertID) FROM project.images WHERE id=?";

    @Override
    public Image add(BufferedImage advertImage, int advertId) throws DAOException {
        Image image = new Image();
        int id = 0;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD_IMAGE, Statement.RETURN_GENERATED_KEYS)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(advertImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            stmt.setBytes(1, imageInByte);
            stmt.setInt(2, advertId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                {
                    if (rs.next()) {
                        id = rs.getInt(1);
                        image.setId(id).setAdvertImage(advertImage).setAdvertId(advertId);
                    }
                }
            }
            advertDAO.updateAdvertDate(advertId);
            return image;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
    @Override
    public void delete(int id) throws DAOException {
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_IMAGE)) {
            Integer advertId = getAdvertIdByImageId(id);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            advertDAO.updateAdvertDate(advertId);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public int getAdvertIdByImageId(int id) throws DAOException {
        Integer advertId = null;
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_ADVERT_ID_BY_IMAGE_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                advertId = rs.getInt(1);
            }
            return advertId;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Image> getByAdvertId(int advertId) throws DAOException {
        List<Image> images = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_BY_ADVERT_ID)) {
            stmt.setInt(1, advertId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Image image = new Image();
                byte[] imageInByte = rs.getBytes(3);
                ByteArrayInputStream bais = new ByteArrayInputStream(imageInByte);
                BufferedImage advertImage = ImageIO.read(bais);

                image.setId(rs.getInt(1)).setAdvertId(rs.getInt(2)).setAdvertImage(advertImage);
                images.add(image);
            }
            return images;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

}
