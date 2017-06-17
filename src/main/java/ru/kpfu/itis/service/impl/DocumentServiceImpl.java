package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Document;
import ru.kpfu.itis.repository.DocumentRepository;
import ru.kpfu.itis.service.DocumentService;

import java.util.List;

/**
 * Created by Safin Ramil on 03.06.17
 * Safin.Ramil@ordotrans.ru
 */

@Service
public class DocumentServiceImpl implements DocumentService {


    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document findOne(String filename) {
        return documentRepository.findByUrl(filename);
    }


    @Override
    public List<Document> findAll(Long userId) {
        return documentRepository.findByUserId(userId);
    }
}
