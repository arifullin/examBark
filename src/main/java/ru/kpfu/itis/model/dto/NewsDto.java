package ru.kpfu.itis.model.dto;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.User;

/**
 * Created by Safin Ramil on 15.05.17
 */
public class NewsDto {

    private User user;

    private String title;

    private String body;

    private MultipartFile[] files;

    private String tags;

    public NewsDto(String title, String body, MultipartFile[] files, String tags) {
        this.title = title;
        this.body = body;
        this.files = files;
        this.tags = tags;
    }

    public NewsDto() {

    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

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

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NewsDto(String title, String body, MultipartFile[] files) {
        this.title = title;
        this.body = body;
        this.files = files;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NewsDto{");
        sb.append("title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", tags='").append(tags).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
