package DAO;

import DAO.Interface.IProductDAO;
import Entity.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl extends BaseDAO implements IProductDAO {


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
        var statement = this.createStatement();
        var resultSet = statement.executeQuery("SELECT * FROM product p.delectedAt is null");
        var products = ConnectionFactory.toList(resultSet,Product.class);
        statement.close();
        return products;
    }

    @Override
    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException {
        String sqlSelectProductByType = """
                SELECT * FROM product WHERE type = ? and deletedAt is null
                """;
        var statement = this.prepareStatement(sqlSelectProductByType);
        statement.setInt(1,type.ordinal());
        var rs = statement.executeQuery();
        return ConnectionFactory.toList(rs,Product.class);
    }

}
