package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Document;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findById(Long id);

    Document findByName(String name);

    Document findByUrl(String url);

    List<Document> findByUserId(Long userId);
}