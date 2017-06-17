package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.Project;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.EventDto;
import ru.kpfu.itis.model.enums.CommunityType;
import ru.kpfu.itis.model.enums.Privileges;
import ru.kpfu.itis.model.forms.EditProjectForm;
import ru.kpfu.itis.model.forms.ProjectEventForm;
import ru.kpfu.itis.model.forms.ProjectForm;
import ru.kpfu.itis.repository.EventRepository;
import ru.kpfu.itis.repository.NewsRepository;
import ru.kpfu.itis.repository.ProjectRepository;
import ru.kpfu.itis.service.EventService;
import ru.kpfu.itis.service.ProjectService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;
import ru.kpfu.itis.service.root.AbstractCrudService;
import ru.kpfu.itis.utils.UploadUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Created by Ramis on 08.05.2017.
 */
@Service
public class ProjectServiceImpl extends AbstractCrudService<Project> implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    StorageService projectImageStorageService;

    @Override
    protected void setRepository() {
        repository = projectRepository;

    }

    @Override
    public List<Project> findByUser(User user) {
        List<Project> projectList = projectRepository.findByUsersContaining(user);
        for (Project project : projectList) {
            if (project.getAvatarUrl() != null) {
                /*
                 FIXME: This is shit, Misha. P.S. Ramil Safin, ask me how to do it normally, please
                 Vlad, don't merge this until Misha fixes it.
                */
                /*
                Path path = projectImageStorageService.loadProjectAvatar(project.getAvatarUrl());
                project.setAvatarUrl("/uploads/Project_Avatar/".concat(path.getFileName().toString()));
                */
            }

        }


        return projectRepository.findByUsersContaining(user);
    }

    @Override
    public void delete(Long id) throws ProjectAccessDeniedException {
        Privileges privileges = checkPrivileges(getById(id));
        if (privileges.equals(Privileges.USER) || privileges.equals(Privileges.ADMIN))
            throw new ProjectAccessDeniedException();
        else projectRepository.delete(id);
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findByProjectName(name);
    }

    @Override
    public void createProject(ProjectForm projectForm, User user) {
        Project project = new Project();
        project.setProjectName(projectForm.getName());
        project.setDescription("");
        project.setCommunityType(CommunityType.PUBLIC);
        if (!projectForm.getAvatar().isEmpty()) {

            String avatarUrl = projectImageStorageService.store(UploadUtils.PROJECTS_PATH, projectForm.getAvatar());

            project.setAvatarUrl(avatarUrl);
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        List<User> admins = new ArrayList<>();
        admins.add(user);
        project.setAdmins(admins);
        project.setLeader(user);
        project.setUsers(users);
        projectRepository.save(project);
    }


    @Override
    public List<Project> getPosts(int page, int num) {
        return projectRepository.findAll(new PageRequest(page, num, new Sort("id"))).getContent();
    }

    @Override
    public void subscribeUser(Long projectId, User user) {
        Project project = getById(projectId);
        if (!project.getUsers().contains(user)) {
            project.getUsers().add(user);
            update(project);
        }
    }

    public Privileges checkPrivileges(Project project) throws ProjectAccessDeniedException {
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        if (project.getLeader().getId().equals(currentUser.getId())) return Privileges.LEADER;
        for (User user : project.getAdmins()) {
            if (user.getId().equals(currentUser.getId())) return Privileges.ADMIN;
        }
        for (User user : project.getUsers()) {
            if (user.getId().equals(currentUser.getId())) return Privileges.USER;
        }
        throw new ProjectAccessDeniedException();
    }

    @Override
    public void deleteUser(Long projectId, User user) {
        Project project = getById(projectId);
        //Privileges privileges = checkPrivileges(project);
        //if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        project.getUsers().remove(user);
        //!!!!!!!!!
        update(project);

    }

    @Override
    public News createPost(Long projectId, News post) throws ProjectAccessDeniedException {
        Project project = getById(projectId);
        Privileges privileges = checkPrivileges(project);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        News news = newsRepository.save(post);
        project.getNews().add(news);
        update(project);
        return news;
    }

    @Override
    public void update(Long projectId, EditProjectForm projectForm) throws ProjectAccessDeniedException {
        Project project = getById(projectId);
        Privileges privileges = checkPrivileges(project);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        project.setDescription(projectForm.getDescription());
        project.setProjectName(projectForm.getName());
        if (projectForm.getAvatar() != null && !projectForm.getAvatar().isEmpty())
            project.setAvatarUrl(projectImageStorageService.store(UploadUtils.PROJECTS_PATH, projectForm.getAvatar()));
        update(project);
    }

    @Override
    public boolean isSubscribed(Long projectId, User user) {
        Project project = projectRepository.getOne(projectId);
        //TODO: fix that shit
        return project.getUsers().contains(user) || project.getAdmins().contains(user) || project.getLeader().getId().equals(user.getId());
    }


    @Override
    public Event createEvent(Long projectId, ProjectEventForm event) throws ProjectAccessDeniedException {
        Project project = getById(projectId);
        Privileges privileges = checkPrivileges(project);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        EventDto eventDto = event.eventDto();
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        eventDto.setAuthor(currentUser);
        Event createdEvent = eventService.save(eventDto);
        project.getEvents().add(createdEvent);
        update(project);
        return createdEvent;
    }

    @Override
    public void deleteAdmin(Long projectId, User user) throws ProjectAccessDeniedException {
        Project project = getById(projectId);
        Privileges privileges = checkPrivileges(project);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        project.getAdmins().remove(user);
        //!!!!!!!!!
        update(project);
    }

    @Override
    public void deleteEvent(Long projectId, Long eventId) {
        Event event = eventService.getById(eventId);
        event.setTags(new HashSet<>());
        eventService.update(event);
        event.setAuthor(null);
        for (User u : event.getUsers()) {
            u.getSubscribedEvents().remove(event);
            userService.update(u);
        }
        event.setUsers(null);
        Project project = getById(projectId);
        project.getEvents().remove(event);
        update(project);
        eventService.delete(eventId);
    }

    @Override
    public void deleteNews(Long projectId, Long newsId) {
        News news = newsRepository.findOne(newsId);
        news.setTags(null);
        newsRepository.save(news);
        Project project = getById(projectId);
        project.getNews().remove(news);
        update(project);
        newsRepository.delete(newsId);
    }

}
