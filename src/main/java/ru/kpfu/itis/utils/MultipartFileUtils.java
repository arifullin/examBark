package ru.kpfu.itis.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Created by Safin Ramil on 08.05.17
 * Safin.Ramil@ordotrans.ru
 */
public class MultipartFileUtils {

    private static final String DELIMITER = "/";

    public static Optional<String> getFileExtension(MultipartFile multipartFile) {
        return getFileMimeType(multipartFile).map(s -> s.split(DELIMITER)[1]);
    }

    public static Optional<String> getFileType(MultipartFile multipartFile) {
        return getFileMimeType(multipartFile).map(s -> s.split(DELIMITER)[0]);
    }

    public static Optional<String> getFileMimeType(MultipartFile multipartFile) {
        if (multipartFile == null) return Optional.empty();
        return Optional.of(multipartFile.getContentType());
    }
}
