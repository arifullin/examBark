package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Tag;

import java.util.List;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    Tag findByName(String name);

    Tag findById(long id);

    @Query("SELECT distinct t FROM News n JOIN n.tags t")
    List<Tag> findByNews();

}
