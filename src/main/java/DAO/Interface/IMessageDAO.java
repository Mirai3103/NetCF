package DAO.Interface;

import Entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface IMessageDAO extends IDAO<Message, Integer> {
     List<Message> findAllBySessionId(int sessionId) throws SQLException ;
}