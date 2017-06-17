package ru.kpfu.itis.model.forms;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.validators.annotations.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Safin Ramil on 08.05.17
 * Safin.Ramil@ordotrans.ru
 */

@FieldMatch(message = "Passwords don't match") // check password and passwordRepeat equality
public class StudentProfileForm implements Serializable {

    protected String phone;

    protected String password;

    protected String passwordRepeat;

    protected MultipartFile avatar;

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    protected MultipartFile document;

    protected String aboutMe;

    public StudentProfileForm() {

    }

    public StudentProfileForm(String phone) {
        this.phone = phone;
    }

    @Size(min = 6, max = 21)
    public String getPhone() {
        return phone;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProfileForm that = (StudentProfileForm) o;
        return Objects.equals(phone, that.phone) &&
                Objects.equals(password, that.password) &&
                Objects.equals(passwordRepeat, that.passwordRepeat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, password, passwordRepeat);
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
