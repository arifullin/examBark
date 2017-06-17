package ru.kpfu.itis.model.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class EventForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Project title can't be empty")
    private String title;

    private String header;

    private String description;

    private String address;

    private MultipartFile avatar;

    private MultipartFile uploadUrl1;

    private MultipartFile uploadUrl2;

    private MultipartFile uploadUrl3;

    private Date date;

    public Long getId() {
        return id;
    }

    public MultipartFile getUploadUrl1() {
        return uploadUrl1;
    }

    public void setUploadUrl1(MultipartFile uploadUrl1) {
        this.uploadUrl1 = uploadUrl1;
    }

    public MultipartFile getUploadUrl2() {
        return uploadUrl2;
    }

    public void setUploadUrl2(MultipartFile uploadUrl2) {
        this.uploadUrl2 = uploadUrl2;
    }

    public MultipartFile getUploadUrl3() {
        return uploadUrl3;
    }

    public void setUploadUrl3(MultipartFile uploadUrl3) {
        this.uploadUrl3 = uploadUrl3;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAvatar(String avatarUrl) {

    }
}
