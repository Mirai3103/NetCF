package service;

import DAO.Interface.IProductDAO;
import lombok.Setter;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    @Setter
    private IProductDAO productDAO;
    @Setter
    private SessionService sessionService;
    public ProductService() {
    }

    public void create(Product product) throws SQLException {
        this.productDAO.create(product);
    }

    public void update(Product product) throws SQLException {
        this.productDAO.update(product);
    }

    public void delete(int integer) throws SQLException {
        this.productDAO.delete(integer);
    }

    public Product findByName(String name) throws SQLException {
        return this.productDAO.findByName(name);
    }

    public List<Product> findAll() throws SQLException {
        return this.productDAO.findAll();
    }

    public List<Product> filterByTypeProduct(Product.ProductType type) throws SQLException {
        return this.productDAO.filterByTypeProduct(type);
    }
}
