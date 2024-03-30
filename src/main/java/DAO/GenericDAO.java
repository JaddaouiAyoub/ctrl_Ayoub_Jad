package DAO;

import java.util.List;

public interface GenericDAO<T, ID> {
    void create(T entity);
    T getById(ID id);
    List<T> getAll();
    void update(T entity);
    void delete(T entity);
}

