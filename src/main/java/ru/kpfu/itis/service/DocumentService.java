package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Document;

import java.util.List;

/**
 * Created by Safin Ramil on 03.06.17
 * Safin.Ramil@ordotrans.ru
 */
@Service
public interface DocumentService {

    Document findOne(String filename);

    List<Document> findAll(Long userId);
}
