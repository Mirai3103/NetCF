package service;

import DAO.Interface.IProductDAO;
import DAO.ProductDAOImpl;
import lombok.Setter;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    @Setter
    private IProductDAO productDAO;
    public List<Product> findAllProduct(){
        try {
            return new ProductDAOImpl().findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Product> findProductByType(Product.ProductType type) {
        try {
            return new ProductDAOImpl().filterByTypeProduct(type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Product findProductById(int id){
        try {
         return   productDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
