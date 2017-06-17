package ru.kpfu.itis.model.forms;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Safin Ramil on 20.05.17
 * Safin.Ramil@ordotrans.ru
 */
public class CreateNewsOrEventForm {

    @NotBlank(message = "Title name can't be empty")
    private String title;
    private String description;
   /* private MultipartFile avatar;
    private boolean isEvent;
    private String address;
    private String date;
    private String hours;
    private String minutes;*/

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

   /* public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public boolean getIsEvent() {
        return isEvent;
    }

    public void setIsEvent(boolean isEvent) {
        this.isEvent = isEvent;
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
    }*/
}
