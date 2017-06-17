package ru.kpfu.itis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Project;
import ru.kpfu.itis.model.User;

import java.util.List;

/**
 * Created by Ramis on 08.05.2017.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUsersContaining(User user);
    Project findByProjectName(String name);

    Page<Project> findAll(Pageable pageable);
}
