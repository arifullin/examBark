package ru.kpfu.itis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Community;
import ru.kpfu.itis.model.User;

import java.util.List;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findByUsersContaining(User user);
    Community findByName(String name);

    Page<Community> findAll(Pageable pageable);

}
