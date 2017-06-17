package ru.kpfu.itis.model.dto;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.Tag;
import ru.kpfu.itis.model.User;

import java.util.LinkedHashSet;
import java.util.Set;

public class EventDto {

    private Long id;

    private String hours;

    private String minutes;

    private String title;

    private String description;

    private String address;

    private String date;

    private MultipartFile[] files;

    private User author;

    private Set<Tag> tags = new LinkedHashSet<>(0);

    private Set<User> users = new LinkedHashSet<>(0);

    public EventDto(String header, String tags, String description, String address, String date, String time1, String time2, MultipartFile[] files) {
        this.title = header;
        this.description = description;
        this.address = address;
        this.date = date;
        this.hours = time1;
        this.minutes = time2;
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setUser(User user) {
        this.author = user;
    }
}