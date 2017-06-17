package ru.kpfu.itis.model;

import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.Sex;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.enums.UserRole;

import javax.persistence.*;
import java.util.*;

/**
 * @author Vladislav Matyunin
 * @see UserRole
 * every user has subscribed communities and events, can bee
 * seen in cabinet
 */
@Entity
@Table(name = "portal_user")
public class User {
    public User(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    @Enumerated(value = EnumType.STRING)
    private SocialStatus socialStatus;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;
    @ManyToMany
    private List<Community> communities;

    @ManyToMany
    private List<Project> projects;

    @ManyToMany
    @JoinTable
    private List<Event> subscribedEvents;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private StudentGroup group;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Document> documents = new LinkedHashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_notifications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private Set<Notification> notifications = new LinkedHashSet<>(0);

    @Column(length = 3000)
    private String aboutMe;

    @Enumerated(value = EnumType.STRING)
    private EducationType education;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public EducationType getEducation() {
        return education;
    }

    public void setEducation(EducationType education) {
        this.education = education;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(List<Community> communities) {
        this.communities = communities;
    }

    public List<Event> getSubscribedEvents() {
        return subscribedEvents;
    }

    public void setSubscribedEvents(List<Event> subscribedEvents) {
        this.subscribedEvents = subscribedEvents;}
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public SocialStatus getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
