package ru.kpfu.itis.model.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by vladislav on 11.05.17.
 */
public class TeacherProfileForm extends StudentProfileForm {

    public TeacherProfileForm(String phone){
        super(phone);
    }
    public TeacherProfileForm(String phone, String name, String surname){
        super(phone);
        this.name = name;
        this.surname = surname;
    }
    public TeacherProfileForm(){}
    @NotBlank
    @Length(max = 100, min = 2)
    protected String name;
    @NotBlank
    @Length(max = 100, min = 2)
    protected String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
