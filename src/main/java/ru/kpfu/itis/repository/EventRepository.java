package ru.kpfu.itis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Event;

import java.util.List;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

    Page<Event> findAllOrOrderByDateStarted(Pageable pageable);

    Page<Event> findAll(Pageable pageable);


    @Query("select e from Event e join fetch e.users u where u.id = :id")
    List<Event> findAllByUser(@Param("id") Long id);
}
