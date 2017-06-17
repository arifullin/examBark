package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.repository.NewsRepository;
import ru.kpfu.itis.repository.TagRepository;
import ru.kpfu.itis.service.TagService;
import ru.kpfu.itis.service.root.AbstractCrudService;

import java.util.List;

/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class TagServiceImpl extends AbstractCrudService<Tag> implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    protected void setRepository() {
        repository = tagRepository;
    }

    @Override
    public List<Tag> getAllTagsByNews() {
        return tagRepository.findByNews();
    }

    @Override
    public Tag findById(long id) {
        return tagRepository.findById(id);
    }
}
