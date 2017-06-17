package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Vladislav Matyunin
 * Provides with private messaging between two users(noone else can see)
 * Something like Notification, may be shown in cabinet or profile of user
 */
@Entity
public class Message {
    public Message(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private Date dateSent;
    @ManyToOne
    private User author;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
