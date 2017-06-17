package ru.kpfu.itis.model.forms;

import javax.validation.constraints.Size;

/**
 * Created by Vlad on 19.05.2017.
 */
public class EditProjectForm extends ProjectForm{
    @Size(max = 5000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
