package ru.kpfu.itis.model;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.service.security.MyUserDetail;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vladislav Matyunin
 *         Event is something, on what all users can subscribe,
 *         so it will be shown in his or her organizer
 *         Has a time period, limited by @dateStarted and @dateFinished
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_started")
    private LocalDateTime dateStarted;

    @Column(name = "date_finished")
    private LocalDateTime dateFinished;

    @Column(name = "date_published")
    private LocalDateTime datePublished;

    @Column
    private String title;

    private String address;

    @Column(length = 1000)
    private String description;

    @Column
    private String uploadUrl1;

    @Column
    private String uploadUrl2;

    @Column
    private String uploadUrl3;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    private User author;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Tag> tags = new LinkedHashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable
    private Set<User> users = new LinkedHashSet<>(0);

    public Event() {

    }

    public Long getId() {
        return id;
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

    public String getUploadUrl1() {
        return uploadUrl1;
    }

    public void setUploadUrl1(String uploadUrl1) {
        this.uploadUrl1 = uploadUrl1;
    }

    public String getUploadUrl2() {
        return uploadUrl2;
    }

    public void setUploadUrl2(String uploadUrl2) {
        this.uploadUrl2 = uploadUrl2;
    }

    public String getUploadUrl3() {
        return uploadUrl3;
    }

    public void setUploadUrl3(String uploadUrl3) {
        this.uploadUrl3 = uploadUrl3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public LocalDateTime getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(LocalDateTime dateFinished) {
        this.dateFinished = dateFinished;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }

    private String getTime(LocalDateTime ldt) {
        int minutesValue = ldt.getMinute();
        String minutes;
        if (minutesValue < 10) {
            minutes = "0" + String.valueOf(minutesValue);
        } else {
            minutes = String.valueOf(minutesValue);
        }
        return String.valueOf(ldt.getHour()) + ":" + minutes;
    }

    private String getDate(LocalDateTime ldt) {
        String day = String.valueOf(ldt.getDayOfMonth());
        int monthValue = ldt.getMonthValue();
        String month;
        if (monthValue < 9) {
            month = "0" + String.valueOf(monthValue);
        } else {
            month = String.valueOf(monthValue);
        }
        String year = String.valueOf(ldt.getYear());
        return day + "." + month + "." + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(dateStarted, event.dateStarted) &&
                Objects.equals(title, event.title) &&
                Objects.equals(dateFinished, event.dateFinished) &&
                Objects.equals(description, event.description);
    }


    public int getUsersCount() {
        return users.size();
    }

    public String getStartedTime() {
        return getTime(dateStarted);
    }

    public String getStartedDate() {
        return getDate(dateStarted);
    }

    public String getCreatedTime() {
        return getTime(datePublished);
    }

    public String getCreatedDate() {
        return getDate(datePublished);
    }

    public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        if (getDateStarted()==null) return "";
        return formatter.format(getDateStarted());
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        if (getDateStarted()==null) return "";
        return formatter.format(getDateStarted());
    }
    public boolean isSubscribed(){
        User currentUser = ((MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//        if (author!=null)
        /*if (author.getEmail().equals(currentUser.getEmail()))
            return true;*/
        for (User u: users){
            if (u.getEmail().equals(currentUser.getEmail()))
                return true;
        }
        return false;
    }
}
