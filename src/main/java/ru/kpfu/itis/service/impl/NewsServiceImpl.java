package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.NewsDto;
import ru.kpfu.itis.model.forms.NewsForm;
import ru.kpfu.itis.repository.NewsRepository;
import ru.kpfu.itis.repository.TagRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.NewsService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.root.AbstractCrudService;
import ru.kpfu.itis.utils.UploadUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class NewsServiceImpl extends AbstractCrudService<News> implements NewsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private TagRepository tagRepository;

    @Override
    protected void setRepository() {
        repository = newsRepository;
    }

    @Override
    public List<News> loadTen(int page) {
        return newsRepository.findAllByOrderByDatePublishedDesc(new PageRequest(page, 10)).getContent();
    }

    @Override
    public List<News> loadFilteredNews(int page, List<Tag> tags) {
        return newsRepository.findDistinctByTagsInOrderByDatePublishedDesc(new PageRequest(page, 10), tags).getContent();
    }

    @Override
    @Transactional
    public News save(NewsDto dto) {

        News news = new News();

        news.setDatePublished(LocalDateTime.now(ZoneId.of("UTC+3")));
        news.setDescription(dto.getBody());
        news.setName(dto.getTitle());

        MultipartFile[] files = dto.getFiles();

        if (dto.getTags() != null) {
            for (String tag : dto.getTags().split(",")) {
                String name = tag.trim();
                Tag t = findTag(name);
                if (t != null) {
                    news.getTags().add(t);
                } else {
                    news.getTags().add(new Tag(name));
                }
            }
        }

        if (files != null) {
            if (files.length > 0 && files[0] != null && files[0].getSize() != 0)
                news.setUpload1(storageService.store(UploadUtils.NEWS_PATH, files[0]));
            if (files.length > 1 && files[1] != null && files[1].getSize() != 0)
                news.setUpload2(storageService.store(UploadUtils.NEWS_PATH, files[1]));
            if (files.length > 2 && files[2] != null && files[2].getSize() != 0)
                news.setUpload3(storageService.store(UploadUtils.NEWS_PATH, files[2]));
        }
        news.setAuthor(dto.getUser());

        return newsRepository.save(news);

    }

    @Override
    public News saveForm(NewsForm newsForm) {
        User currentUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        News currentNews = new News(newsForm.getTitle(), newsForm.getBody(), LocalDateTime.now(ZoneId.of("UTC+3")),
                currentUser, new HashSet<>(tagRepository.save(tags(newsForm.getTags()))));
        if (newsForm.getFile1() != null) currentNews.setUpload1(storageService.store(UploadUtils.NEWS_PATH, newsForm.getFile1()));
        if (newsForm.getFile2() != null) currentNews.setUpload2(storageService.store(UploadUtils.NEWS_PATH, newsForm.getFile2()));
        if (newsForm.getFile3() != null) currentNews.setUpload3(storageService.store(UploadUtils.NEWS_PATH, newsForm.getFile3()));
        return newsRepository.saveAndFlush(currentNews);
    }

    private Tag findTag(String name) {
        return tagRepository.findByName(name);
    }

    public List<Tag> tags(String tagString) {
        ArrayList<Tag> tagList = new ArrayList<>();
        if (tagString != null) {
            for (String tag : tagString.split(",")) {
                String name = tag.trim();
                Tag t = tagRepository.findByName(name);
                if (t != null) {
                    tagList.add(t);
                } else {
                    tagList.add(new Tag(tag));
                }
            }
        }
        return tagList;
    }

}
