package ru.kpfu.itis.model.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.service.TagService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Safin Ramil on 20.05.17
 * Safin.Ramil@ordotrans.ru
 */
@Component
public class NewsForm {
    @Autowired
    private TagService tagService;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 1000)
    private String title;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 10000)
    private String body;

    @Size(max = 100)
    private String tags;
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public MultipartFile getFile1() {
        return file1;
    }

    public void setFile1(MultipartFile file1) {
        this.file1 = file1;
    }

    public MultipartFile getFile2() {
        return file2;
    }

    public void setFile2(MultipartFile file2) {
        this.file2 = file2;
    }

    public MultipartFile getFile3() {
        return file3;
    }

    public void setFile3(MultipartFile file3) {
        this.file3 = file3;
    }


}
