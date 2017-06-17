package ru.kpfu.itis.model;

import org.hibernate.validator.constraints.NotBlank;
import ru.kpfu.itis.model.enums.CommunityType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Vladislav Matyunin
 *         <p>
 *         This entity is the same, as VK's one
 *         Has a tag for easy search(logical attribute, field @communityTag)
 *         Can be private and public (field @communityType)
 */
@Entity
public class Community {

    public Community() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;

    @Enumerated(value = EnumType.STRING)
    private CommunityType communityType;

    @ManyToMany
    @JoinTable
    private List<User> users;

    private Date dateCreated;

    @ManyToOne
    private Tag communityTag;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @ManyToMany
    private List<User> admins;

    @OneToMany
    @JoinColumn()
    private List<News> news;

    @OneToMany
    @JoinColumn
    private List<Event> events;

    private String description;

    @ManyToOne
    private User leader;

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
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

    public CommunityType getCommunityType() {
        return communityType;
    }

    public void setCommunityType(CommunityType communityType) {
        this.communityType = communityType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Tag getCommunityTag() {
        return communityTag;
    }

    public void setCommunityTag(Tag communityTag) {
        this.communityTag = communityTag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
