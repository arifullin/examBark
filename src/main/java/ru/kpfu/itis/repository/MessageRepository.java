package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Message;

/**
 * Created by vladislav on 18.04.17.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
}
