package ru.kpfu.itis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Safin Ramil on 08.05.17
 * Safin.Ramil@ordotrans.ru
 */

@Entity
@Table(name = "notifications")
public class Notification implements Serializable {

    private Long id;

    private String title;

    private String body;

    private LocalDateTime created;

    private LocalDate deadline;

    private LocalTime time;

    private User author;

    private Set<User> users = new LinkedHashSet<>(0);

    public Notification() {

    }

    public Notification(String title, String body, LocalDate deadline, User author) {
        this.title = title;
        this.body = body;
        this.deadline = deadline;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "body")
    public String getBody() {
        return body;
    }

    @Column(name = "created")
    public LocalDateTime getCreated() {
        return created;
    }

    @Column(name = "deadline")
    public LocalDate getDeadline() {
        return deadline;
    }

    @Column(name = "time")
    public LocalTime getTime() {
        return time;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    @ManyToMany(mappedBy = "notifications")
    public Set<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if (!users.contains(user))
            this.users.add(user);
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(body, that.body) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, created);
    }
}
