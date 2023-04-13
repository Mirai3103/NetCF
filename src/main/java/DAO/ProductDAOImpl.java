package DAO;

import DAO.Interface.IProductDAO;
import model.Product;

import java.beans.Statement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {


    @Override
    public Product create(Product product) throws SQLException {
        var preprapedStament = ConnectionFactory.getConnection().prepareStatement("INSERT INTO Product(name, price, type, stok, description, image, createAt, deletedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        return null;
    }

    @Override
    public Product update(Product product) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Product findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }
}
