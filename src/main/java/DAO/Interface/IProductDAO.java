package DAO.Interface;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO extends IDAO<Product, Integer>{
    public Product findByName(String name) throws SQLException;
    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException;
}
