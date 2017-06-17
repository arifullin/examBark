package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Vladislav Matyunin
 * Tags are made for easy and logical search
 */
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Tag() {
    }

    @ManyToMany
    @JoinTable
    private List<News> news;

    public Tag(Long id) {
        this.id = id;
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
