package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);

    User findByEmailIgnoreCase(String email);

    Optional<User> findByNameIgnoreCase(String name);

    List<User> findAllByUserRole(UserRole userRole);

    @Query("select u from User u where u.id in :ids")
    List<User> findAllByIdsIn(@Param("ids") List<Long> ids);
}
