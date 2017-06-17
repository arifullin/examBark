package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.service.root.CrudService;

import java.util.List;

/**
 * @author Matyunin Vladislav
 * @see Tag entity in package .model
 * interface to work with tags
 */
@Service
public interface TagService extends CrudService<Tag> {
    /**
     * @param name - the name of tag
     * @return only one tag, because the name must be unique
     */
    Tag findByName(String name);

    List<Tag> getAllTagsByNews();

    Tag findById(long id);
}
