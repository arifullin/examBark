package ru.kpfu.itis.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Vladislav Matyunin
 * News is a static content, published once, seen by everybody
 *
 */
@Entity
public class News {

    private static final int DESCRIPTION_DIVISION_SYMBOL = 228;  //Be careful

    public News(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 10000)
    private String description;
    private LocalDateTime datePublished;

    public News(String name, String description, LocalDateTime datePublished, User author, Set<Tag> tags) {
        this.name = name;
        this.description = description;
        this.datePublished = datePublished;
        this.author = author;
        this.tags = tags;
    }

    @ManyToOne
    private User author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Tag> tags = new LinkedHashSet<>(0);

    private String upload1;
    private String upload2;
    private String upload3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
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

    public String getUpload1() {
        return upload1;
    }

    public void setUpload1(String upload1) {
        this.upload1 = upload1;
    }

    public String getUpload2() {
        return upload2;
    }

    public void setUpload2(String upload2) {
        this.upload2 = upload2;
    }

    public String getUpload3() {
        return upload3;
    }

    public void setUpload3(String upload3) {
        this.upload3 = upload3;
    }

    public String getTime() {
        LocalDateTime ldt = getDatePublished();
        return String.valueOf(ldt.getHour()) + ":" + String.valueOf(ldt.getMinute());
    }

    public String getDate() {
        LocalDateTime ldt = getDatePublished();
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

   /* public String getFirstPartOfDescription() {
        String fullDescr = getDescription();
        return fullDescr.substring(0, Math.min(fullDescr.length(), DESCRIPTION_DIVISION_SYMBOL));
    }

    public String getSecondPartOfDescription() {
        String fullDescr = getDescription();
        String resString;
        try {
            resString = fullDescr.substring(Math.min(fullDescr.length(), DESCRIPTION_DIVISION_SYMBOL), fullDescr.length());
          *//*  if(resString.isEmpty()){
                return null;
            }*//*
        } catch (IndexOutOfBoundsException ex) {
            return "";
        }
        return resString;
    }*/
}
