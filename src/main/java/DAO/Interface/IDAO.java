package DAO.Interface;

import java.util.Collection;

public interface IDAO<T, ID> {
    T create(T t);
    T update(T t);
    int delete(ID id);
    T findById(ID id);
    Collection<T> findAll();
}

