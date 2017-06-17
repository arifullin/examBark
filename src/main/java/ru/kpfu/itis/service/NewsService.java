package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.model.dto.NewsDto;
import ru.kpfu.itis.model.forms.NewsForm;
import ru.kpfu.itis.service.root.CrudService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Vladislav Matyunin
 */
@Service
public interface NewsService extends CrudService<News> {
    /**
     * This method works as pagination, pay attention, that
     * the default sort is by Date(the newest)
     * @param page - the number of page, starts from 1 [Attention!!!]
     * @return all news on this page
     */
    List<News> loadTen(int page);
    List<News> loadFilteredNews(int page, List<Tag> tags);

    @Transactional
    News save(NewsDto dto);
    News saveForm(NewsForm newsForm);

}
