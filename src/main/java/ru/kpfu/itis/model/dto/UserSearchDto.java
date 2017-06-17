package ru.kpfu.itis.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Safin Ramil on 13.05.17
 */
public class UserSearchDto {

    private Long userId;

    private Integer course;
    private Integer group;

    private String name;
    private String surname;
    private String patronymic;

    private Boolean isMan;
    private Boolean isWoman;

    private Boolean isGrant;
    private Boolean isBudget;
    private Boolean isContract;


    private Boolean isStarosta;
    private Boolean isProfOrg;
    private Boolean isSportOrg;
    private Boolean isSocialOrg;
    private Boolean isCultureOrg;


    public Integer getCourse() {
        return course;
    }

    public Integer getGroup() {
        return group;
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

    @JsonProperty("isMan")
    public Boolean getMan() {
        return isMan;
    }

    @JsonProperty("isWoman")
    public Boolean getWoman() {
        return isWoman;
    }

    @JsonProperty("isGrant")
    public Boolean getGrant() {
        return isGrant;
    }

    @JsonProperty("isBudget")
    public Boolean getBudget() {
        return isBudget;
    }

    @JsonProperty("isContract")
    public Boolean getContract() {
        return isContract;
    }

    @JsonProperty("isStarosta")
    public Boolean getStarosta() {
        return isStarosta;
    }

    @JsonProperty("isProfOrg")
    public Boolean getProfOrg() {
        return isProfOrg;
    }

    @JsonProperty("isSportOrg")
    public Boolean getSportOrg() {
        return isSportOrg;
    }

    @JsonProperty("isSocialOrg")
    public Boolean getSocialOrg() {
        return isSocialOrg;
    }

    @JsonProperty("isCultureOrg")
    public Boolean getCultureOrg() {
        return isCultureOrg;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public void setGroup(Integer group) {
        this.group = group;
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

    public void setMan(Boolean man) {
        isMan = man;
    }

    public void setWoman(Boolean woman) {
        isWoman = woman;
    }

    public void setGrant(Boolean grant) {
        isGrant = grant;
    }

    public void setBudget(Boolean budget) {
        isBudget = budget;
    }

    public void setContract(Boolean contract) {
        isContract = contract;
    }

    public void setStarosta(Boolean starosta) {
        isStarosta = starosta;
    }

    public void setProfOrg(Boolean profOrg) {
        isProfOrg = profOrg;
    }

    public void setSportOrg(Boolean sportOrg) {
        isSportOrg = sportOrg;
    }

    public void setSocialOrg(Boolean socialOrg) {
        isSocialOrg = socialOrg;
    }

    public void setCultureOrg(Boolean cultureOrg) {
        isCultureOrg = cultureOrg;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserSearchDto{");
        sb.append("course=").append(course);
        sb.append(", group=").append(group);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", isMan=").append(isMan);
        sb.append(", isWoman=").append(isWoman);
        sb.append(", isGrant=").append(isGrant);
        sb.append(", isBudget=").append(isBudget);
        sb.append(", isContract=").append(isContract);
        sb.append(", isStarosta=").append(isStarosta);
        sb.append(", isProfOrg=").append(isProfOrg);
        sb.append(", isSportOrg=").append(isSportOrg);
        sb.append(", isSocialOrg=").append(isSocialOrg);
        sb.append(", isCultureOrg=").append(isCultureOrg);
        sb.append('}');
        return sb.toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
