package DAO;

import DAO.Interface.IInvoiceDAO;
import Entity.InforFilter;
import Entity.Invoice;

import java.sql.*;
import java.util.List;

public class InvoiceDAOImpl extends BaseDAO implements IInvoiceDAO {
    @Override
    public Invoice create(Invoice invoice) throws SQLException {
        try(var preparedStatement = this.prepareStatement("insert into Invoice (computerId, createdAt, createdBy, createdToAccountId, deletedAt, isPaid, note, status, total, type) values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, invoice.getComputerId());
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(invoice.getCreatedAt().getTime()));
            preparedStatement.setInt(3, invoice.getCreatedBy());
            preparedStatement.setInt(4, invoice.getCreatedToAccountId());
            preparedStatement.setNull(5, Types.TIMESTAMP);
            preparedStatement.setBoolean(6, invoice.isPaid());
            preparedStatement.setString(7, invoice.getNote());
            preparedStatement.setInt(8, invoice.getStatus().ordinal());
            preparedStatement.setDouble(9, invoice.getTotal());
            preparedStatement.setInt(10, invoice.getType().ordinal());

            var result = preparedStatement.executeUpdate();
            if (result > 0) {
                var resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    var newId = resultSet.getInt(1);
                    return this.findById(newId);
                }
            }
        }
        return null;
    }

    @Override
    public Invoice update(Invoice invoice) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        String sqlUpdateInvoiceById = """
                UPDATE invoice
                SET deletedAt = getdate()
                WHERE id = ?;
                """;
        var stt = this.prepareStatement(sqlUpdateInvoiceById);
        stt.setInt(1,integer);
        var rowEffect = stt.executeUpdate();
        stt.close();
        return rowEffect > 0 ? true:false;
    }

    @Override
    public Invoice findById(Integer integer) throws SQLException{
        String sqlSelectById = """
                select *
                from invoice
                where id = ? and deletedAt is null;
                """;
        var stt = this.prepareStatement(sqlSelectById);
        stt.setInt(1,integer);
        var rs = stt.executeQuery();
        var invoices = ConnectionFactory.toList(rs,Invoice.class);
        stt.close();
        return invoices.size() > 0 ? invoices.get(0): null;
    }

    @Override
    public List<Invoice> findAll() throws SQLException {

            String sqlSelectALlRow = "select * " +
                    "from Invoice " +
                    "where deletedAt is null";
            var stt =this.createStatement();
            var rs = stt.executeQuery(sqlSelectALlRow);
            var listInvoice = ConnectionFactory.toList(rs, Invoice.class);
            stt.close();
        return listInvoice;
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
        var listInvoice = ConnectionFactory.toList(rs, Invoice.class);
        stt.close();
        return listInvoice;
    }

    @Override
    public List<Invoice> findInvoiceByInforFilter(Invoice.InvoiceType type, InforFilter inforFilter) throws SQLException {
        String sqlSelectInvoiceByInforFilter = """
                 select *
                                from Invoice\s
                                where ((createdAt between ? and ?)
                                and (total between ? and  ?)
                                and ((createdToAccountId is NULL) or (createdToAccountId = ?))
                                and computerId = ?
                                and createdBy = ?
                 			   and deletedAt is NULL
                 			   and type = ?)
                """;
        var stt = this.prepareStatement(sqlSelectInvoiceByInforFilter);
        stt.setString(1,inforFilter.getDateFrom());
        stt.setString(2,inforFilter.getDateTo());
        stt.setDouble(3,Double.parseDouble(inforFilter.getTotalFrom()));
        stt.setDouble(4,Double.parseDouble(inforFilter.getTotalTo()));
        stt.setInt(5,inforFilter.getAccountID());
        stt.setInt(6,inforFilter.getComputerID());
        stt.setInt(7,inforFilter.getEmployeeID());
        stt.setInt(8,type.ordinal());
        var rs = stt.executeQuery();
        var listInvoice = ConnectionFactory.toList(rs,Invoice.class);
        stt.close();
        return listInvoice;
    }

}

