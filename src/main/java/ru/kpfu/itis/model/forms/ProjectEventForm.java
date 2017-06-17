package ru.kpfu.itis.model.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.dto.EventDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Vlad on 01.06.2017.
 */
public class ProjectEventForm {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 1000)
    private String header;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 10000)
    private String description;

    private String tags;

    private MultipartFile file1;

    private MultipartFile file2;

    private MultipartFile file3;
    @NotNull
    @NotBlank
    private String date;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    private String time1;
    @NotNull
    @NotBlank
    private String time2;
    public EventDto eventDto(){
        return new EventDto(header,tags,description,address,date,time1,time2,new MultipartFile[]{file1,file2,file3});
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
