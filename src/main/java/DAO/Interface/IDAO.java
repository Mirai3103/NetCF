package DAO.Interface;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

public interface IDAO<T, ID> {
    T create(T t);
    T update(T t);
    int delete(ID id);
    T findById(ID id);
    List<T> findAll() throws ParseException;
}

