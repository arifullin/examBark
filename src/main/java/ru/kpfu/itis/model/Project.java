package ru.kpfu.itis.model;

import org.hibernate.validator.constraints.NotBlank;
import ru.kpfu.itis.model.enums.CommunityType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Ramis on 08.05.2017.
 */
@Entity

//FIXME: Remake, it's fake
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User leader;

    @NotNull
    @NotBlank
    @Column(unique = true, name = "name")
    private String projectName;

    private String description;

    private String avatarUrl;

    @ManyToMany
//    @NotNull
    @JoinTable
    private List<User> admins;

    @OneToMany
    private List<News> news;

    @OneToMany
    private List<Event> events;
    @ManyToMany
    @JoinTable
    private List<User> users;

    @Enumerated(value = EnumType.STRING)
    private CommunityType communityType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public int getUsersCount(){
        return users.size();
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
    public int getTotalUserCount(){
        return users.size();
    }

    public CommunityType getCommunityType() {
        return communityType;
    }

    public void setCommunityType(CommunityType communityType) {
        this.communityType = communityType;
    }
}
