package ru.kpfu.itis.controller.uploads;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.model.Document;
import ru.kpfu.itis.service.DocumentService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.utils.UploadUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Safin Ramil on 16.05.17
 * Safin.Ramil@ordotrans.ru
 */
@Controller
public class UploadsController {

    private static final Logger logger = LoggerFactory.getLogger(UploadsController.class);

    private final StorageService storageService;

    private final DocumentService documentService;

    @Autowired
    public UploadsController(StorageService storageService, DocumentService documentService) {
        this.storageService = storageService;
        this.documentService = documentService;
    }


    @ResponseBody
    @GetMapping("/uploads/news/{filename:.+}")
    public byte[] uploadNewsImages(@PathVariable("filename") String filename) {
        try {
            return IOUtils.toByteArray(storageService.loadAsResource(UploadUtils.NEWS_PATH, filename).getInputStream());
        } catch (IOException e) {
            return new byte[]{};
        }
    }

    @ResponseBody
    @GetMapping("/uploads/events/{filename:.+}")
    public byte[] uploadEventsImages(@PathVariable("filename") String filename) {
        try {
            return IOUtils.toByteArray(storageService.loadAsResource(UploadUtils.EVENTS_PATH, filename).getInputStream());
        } catch (IOException e) {
            return new byte[]{};
        }
    }

    @ResponseBody
    @GetMapping("/uploads/profile/{filename:.+}")
    public byte[] uploadProfileImages(@PathVariable("filename") String filename) {
        try {
            return IOUtils.toByteArray(storageService.loadAsResource(UploadUtils.USERS_PATH, filename).getInputStream());
        } catch (IOException e) {
            return new byte[]{};
        }
    }

    @ResponseBody
    @GetMapping("/uploads/Project_Avatar/{filename:.+}") // NOTE: use regexp, 'cause spring truncates all after '.'
    public byte[] getImage(@PathVariable("filename") String filename) {
        Resource resource = storageService.loadAsResource(UploadUtils.PROJECTS_PATH, filename);
        try {
            return IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException | RuntimeException e) {
            return new byte[0];
        }
    }

    @ResponseBody
    @GetMapping("/uploads/User_Avatar/{filename:.+}") // NOTE: use regexp, 'cause spring truncates all after '.'
    public byte[] getUserImage(@PathVariable("filename") String filename) {
        Resource resource = storageService.loadAsResource(UploadUtils.USERS_PATH, filename);
        try {
            return IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException | RuntimeException e) {
            return new byte[0];
        }
    }

    @ResponseBody
    @GetMapping("/uploads/Community_Avatar/{filename:.+}") // NOTE: use regexp, 'cause spring truncates all after '.'
    public byte[] getCommunityImage(@PathVariable("filename") String filename) {
        Resource resource = storageService.loadAsResource(UploadUtils.COMMUNITIES_PATH, filename);
        try {
            return IOUtils.toByteArray(resource.getInputStream());
        } catch (IOException | RuntimeException e) {
            return new byte[0];
        }
    }


    @ResponseBody
    @GetMapping("/uploads/documents/{filename:.+}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(UploadUtils.DOCUMENTS_PATH, filename);

        Document document = documentService.findOne(filename);


        if (document == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", document.getName()))
                .body(file);
    }


    @ResponseBody
    @GetMapping(value = "/uploads/documents/user/{userId}")
    public void downloadAllDocuments(
            @PathVariable Long userId,
            HttpServletResponse resp
    ) {

        resp.setHeader("Content-Type", "application/zip");
        resp.setHeader("Content-Disposition", "attachment; filename=\"archive.zip\"");

        List<Document> documents = documentService.findAll(userId);

        if (documents == null) {
            resp.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }

        try (
                ZipOutputStream zip = new ZipOutputStream(resp.getOutputStream())
        ) {

            documents.forEach(document -> {

                try {

                    Path path = storageService.load(UploadUtils.DOCUMENTS_PATH, document.getUrl()); // load document by url

                    zip.putNextEntry(new ZipEntry(document.getId() + "-" + document.getName()));

                    zip.write(Files.readAllBytes(path));

                    zip.closeEntry();

                } catch (IOException e) {
                    logger.error(e.getMessage());
                    try {
                        zip.write((e.getMessage() + " - " + LocalDateTime.now(ZoneId.of("UTC+3"))).getBytes());
                    } catch (IOException ignored) {}
                }

            });

        } catch (IOException e) {
            throw new RuntimeException("Can't create zip archive");
        }
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        return "redirect:/error";
    }
}
