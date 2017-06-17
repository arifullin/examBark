package ru.kpfu.itis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.Tag;

import java.util.List;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAllByOrderByDatePublishedDesc(Pageable pageable);

    Page<News> findDistinctByTagsInOrderByDatePublishedDesc(Pageable pageable, List<Tag> tags);
}
