package ru.kpfu.itis.model.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel Shchepetov on 13.05.2017.
 */
public class ProjectForm {


    @NotBlank(message = "Project name can't be empty")
    protected String name;

    protected MultipartFile avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }


}
