package DAO;

import DAO.BaseDAO;
import DAO.ConnectionFactory;
import DAO.Interface.IInvoiceDAO;
import model.Invoice;

import java.sql.SQLException;
import java.util.List;

public class InvoiceDAOImpl extends BaseDAO implements IInvoiceDAO {
    @Override
    public Invoice create(Invoice invoice) throws SQLException {
        return null;
    }

    @Override
    public Invoice update(Invoice invoice) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Invoice findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public List<Invoice> findAll() throws SQLException {

            String sqlSelectALlRow = "select * " +
                    "from Invoice " +
                    "where deletedAt is null";
            var stt =this.createStatement();
            var rs = stt.executeQuery(sqlSelectALlRow);

        return ConnectionFactory.toList(rs, Invoice.class);
    }

    @Override
    public List<Invoice> findAllByType(Invoice.InvoiceType type) throws SQLException {
        String sqlSelectALlRow = """
                select *
                 from Invoice
                 where deletedAt is null and type = ?""";
        var stt =this.prepareStatement(sqlSelectALlRow);
        stt.setInt(1,type.ordinal());
        var rs = stt.executeQuery();
        return ConnectionFactory.toList(rs, Invoice.class);
    }
}
