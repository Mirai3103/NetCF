package DAO.Interface;

import model.Session;

import java.sql.SQLException;

public interface ISessionDAO extends IDAO<Session, Integer> {
     Session findByComputerId(int computerId ) throws SQLException;
}
