package ru.kpfu.itis.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Safin Ramil on 08.05.17
 */
@Service
public interface StorageService {

    Path load(String filename);

    Path load(Path path, String filename);

    Resource loadAsResource(String filename);

    Resource loadAsResource(Path path, String filename);

    String store(MultipartFile multipartFile);

    String store(Path path, MultipartFile multipartFile);

    Stream<Path> loadAll();

    Stream<Path> loadAll(Path path);

    void init();
}
