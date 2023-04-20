package DAO.Interface;

import Entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO extends IDAO<Product, Integer>{
    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException;
}
