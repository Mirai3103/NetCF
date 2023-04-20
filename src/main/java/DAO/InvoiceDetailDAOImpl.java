package DAO;

import DAO.Interface.IInvoiceDetailDAO;
import Entity.InvoiceDetail;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InvoiceDetailDAOImpl extends BaseDAO implements IInvoiceDetailDAO {
    @Override
    public InvoiceDetail create(InvoiceDetail invoiceDetail) throws SQLException {
        var preparedStatement = this.prepareStatement("insert into InvoiceDetail (invoiceId, productId, quantity) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, invoiceDetail.getInvoiceId());
        preparedStatement.setInt(2, invoiceDetail.getProductId());
        preparedStatement.setInt(3, invoiceDetail.getQuantity());
        var result = preparedStatement.executeUpdate();
        if (result > 0) {
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                var newId = resultSet.getInt(1);
                return this.findById(newId);
            }
        }
        return null;
    }

    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public InvoiceDetail findById(Integer integer) throws SQLException {
        var sql = "select * from InvoiceDetail where id = ?";
        var preparedStatement = this.prepareStatement(sql);
        preparedStatement.setInt(1, integer);
        var resultSet = preparedStatement.executeQuery();
        var list = ConnectionFactory.toList(resultSet, InvoiceDetail.class);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<InvoiceDetail> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<InvoiceDetail> findAllByInvoiceId(Integer invoiceId) {
        return null;
    }
}
