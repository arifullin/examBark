package ru.kpfu.itis.service.root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by vladislav on 30.04.17.
 */
@Component
public abstract class AbstractCrudService<T> implements CrudService<T> {

    @PostConstruct
    private void init() {
        setRepository();
    }

    protected JpaRepository<T, Long> repository;

    protected abstract void setRepository();

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void update(T item) {
        repository.saveAndFlush(item);
    }

    @Override
    public void add(T item) {
        repository.saveAndFlush(item);
    }

}
