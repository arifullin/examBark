package ru.kpfu.itis.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.kpfu.itis.model.Document;
import ru.kpfu.itis.model.StudentGroup;
import ru.kpfu.itis.model.enums.EducationType;
import ru.kpfu.itis.model.enums.Sex;
import ru.kpfu.itis.model.enums.SocialStatus;
import ru.kpfu.itis.model.enums.UserRole;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Safin Ramil on 13.05.17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSearchResultDto implements Serializable {

    private Long id;

    private String name;
    private String surname;
    private String patronymic;

    private String email;
    private String phone;

    private Sex sex;
    private UserRole role;
    private String avatarUrl;
    private SocialStatus socialStatus;
    private StudentGroup group;
    private EducationType education;

    // TODO add necessary information (projects, documents etc.)

    private List<Document>  documents;

    public UserSearchResultDto() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Sex getSex() {
        return sex;
    }

    public UserRole getRole() {
        return role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSearchResultDto that = (UserSearchResultDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                sex == that.sex &&
                Objects.equals(role, that.role) &&
                Objects.equals(avatarUrl, that.avatarUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, email,
                phone, sex, role, avatarUrl);
    }

    public SocialStatus getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(SocialStatus socialStatus) {
        this.socialStatus = socialStatus;
    }

    public StudentGroup getGroup() {
        return group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    public EducationType getEducation() {
        return education;
    }

    public void setEducation(EducationType education) {
        this.education = education;
    }
}
