package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.Project;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.Privileges;
import ru.kpfu.itis.model.forms.EditProjectForm;
import ru.kpfu.itis.model.forms.ProjectEventForm;
import ru.kpfu.itis.model.forms.ProjectForm;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;
import ru.kpfu.itis.service.root.CrudService;

import java.util.List;

/**
 * Created by Ramis on 08.05.2017.
 */
public interface ProjectService extends CrudService<Project> {
    /**
     * Get all user's projects
     *
     * @param user - the user, whom to work with
     * @return all user's projects
     */

    List<Project> findByUser(User user);
    Privileges checkPrivileges(Project project) throws ProjectAccessDeniedException;
    Project findByName(String name);
    void createProject(ProjectForm projectForm, User user);

    List<Project> getPosts(int page, int num);
    void subscribeUser(Long projectId, User user);
    void deleteUser(Long projectId, User user);
    News createPost(Long projectId,News post) throws ProjectAccessDeniedException;
    void update(Long projectId, EditProjectForm projectForm) throws ProjectAccessDeniedException;
    boolean isSubscribed(Long projectId, User user);

    Event createEvent(Long projectId, ProjectEventForm event) throws ProjectAccessDeniedException;

    void deleteAdmin(Long projectId, User user) throws ProjectAccessDeniedException;
    void deleteEvent(Long projectId, Long eventId);
    void deleteNews(Long projectId, Long newsId);
}
