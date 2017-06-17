package ru.kpfu.itis.service.root;

import java.util.List;

/**
 * Created by vladislav on 18.04.17.
 */
public interface CrudService<T> {

    List<T> getAll();

    T getById(Long id);

    void delete(Long id);

    void update(T item);

    void add(T item);
}
