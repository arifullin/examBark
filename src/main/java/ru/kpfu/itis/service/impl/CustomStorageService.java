package ru.kpfu.itis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.exceptions.StorageException;
import ru.kpfu.itis.exceptions.StorageFileNotFoundException;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.utils.MultipartFileUtils;
import ru.kpfu.itis.utils.UploadUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by Safin Ramil on 08.05.17
 */

@Service
public class CustomStorageService implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(CustomStorageService.class);

    private final Environment environment;

    private final Path rootLocation; // root location from environment

    @Autowired
    public CustomStorageService(Environment environment) {
        this.environment = environment;
        this.rootLocation = Paths.get(environment.getRequiredProperty("system.upload.location"));
    }


    @Override
    public Path load(String filename) {
        return load(Paths.get(""), filename);
    }

    @Override
    public Path load(Path path, String filename) {

        path = rootLocation.resolve(path);

        logger.warn(String.format("Trying to load file '%s' with path '%s'",
                filename, path.toAbsolutePath()));

        return path.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        return loadAsResource(Paths.get(""), filename);
    }

    @Override
    public Resource loadAsResource(Path path, String filename) {

        logger.warn(String.format("Trying to load as resource file '%s' with path '%s'",
                filename, rootLocation.resolve(path).toAbsolutePath()));

        try {

            Path file = load(path, filename);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            }

            throw new StorageFileNotFoundException(String.format("Could not read file '%s' with path '%s'",
                    filename, rootLocation.resolve(path).toAbsolutePath()));

        } catch (MalformedURLException e) {
            throw new StorageException(String.format("Couldn't read file '%s' with path '%s'",
                    filename, rootLocation.resolve(path).toAbsolutePath()), e);
        }
    }

    @Override
    public String store(MultipartFile multipartFile) {
        return store(Paths.get(""), multipartFile);
    }

    @Override
    public String store(Path path, MultipartFile multipartFile) {

        logger.warn(String.format("Trying to save file '%s' with path '%s'",
                multipartFile.getOriginalFilename(), rootLocation.resolve(path).toAbsolutePath()));

        path = rootLocation.resolve(path);

        if (multipartFile.isEmpty()) {
            throw new StorageException(String.format("Failed to load an empty file '%s' with path '%s'",
                    multipartFile.getOriginalFilename(), path.toAbsolutePath()));
        }

        try {

            String generatedFilename = generateFilename(multipartFile);

            // save file to uploads
            Files.copy(multipartFile.getInputStream(), path.resolve(generatedFilename));

            return generatedFilename;

        } catch (IOException | RuntimeException e) {

            throw new StorageException(String.format("Failed to store file '%s' with path '%s'",
                    multipartFile.getOriginalFilename(), path.toAbsolutePath()));
        }
    }

    // Util method for generating random unique filename
    private String generateFilename(MultipartFile multipartFile) {

        // generate uuid filename
        StringBuilder generatedFilename = new StringBuilder(UUID.nameUUIDFromBytes((multipartFile.getOriginalFilename()
                + LocalDateTime.now(ZoneId.of("UTC+3"))).getBytes()).toString());

        MultipartFileUtils.getFileExtension(multipartFile).ifPresent(extension -> {
            generatedFilename.append('.').append(extension);
        });

        return generatedFilename.toString();
    }

    @Override
    public Stream<Path> loadAll() {
        return loadAll(Paths.get(""));
    }

    @Override
    public Stream<Path> loadAll(Path path) {

        try {

            return Files.walk(path, 1)
                    .filter(p -> !rootLocation.resolve(path).equals(p))
                    .map(rootLocation.resolve(path)::relativize);

        } catch (IOException e) {
            throw new StorageException(String.format("Failed to load all files with path '%s'",
                    rootLocation.resolve(path).toAbsolutePath()));
        }
    }


    @Override
    @PostConstruct // to invoke it automatically
    public void init() {

        logger.warn("Trying to initialize storage service");

        try {

            if (!rootLocation.toFile().exists()) {
                Files.createDirectory(rootLocation); // create root upload directory
            }

            for (String path : UploadUtils.PATHS) {

                Path currentPath = rootLocation.resolve(path);

                if (!currentPath.toFile().exists()) {
                    Files.createDirectory(currentPath);
                }
            }

        } catch (IOException e) {
            throw new StorageException("Couldn't initialize storage service");
        }
    }
}
