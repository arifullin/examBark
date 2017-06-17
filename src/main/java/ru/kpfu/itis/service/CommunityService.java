package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Community;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.Privileges;
import ru.kpfu.itis.model.forms.CreateCommunityForm;
import ru.kpfu.itis.model.forms.EditProjectForm;
import ru.kpfu.itis.model.forms.ProjectEventForm;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;
import ru.kpfu.itis.service.root.CrudService;

import java.util.List;

/**
 * @author Vladislav Matyunin
 */
@Service
public interface CommunityService extends CrudService<Community> {
    /**
     * Works like MY_COMMUNITIES in VK
     * @param user - the user, whom to work with
     * @return all subscribed groups of user
     */
    List<Community> findByUser(User user);

    List<Community> getGroups(int page, int num);

    void subscribeUser(Long communityId, User user);

    void unSubscribeUser(Long communityId, User user);

//    void createPost(Long communityId, CreateNewsOrEventForm form, User user);

    boolean isSubscribed(Long projectId, User user);

    void createCommunity(CreateCommunityForm projectForm, User user);

    Privileges checkPrivileges(Community community) throws ProjectAccessDeniedException;

    News createPost(Long projectId, News post) throws ProjectAccessDeniedException;

    Event createEvent(Long projectId, ProjectEventForm event) throws ProjectAccessDeniedException;

//    void update(Long communityId, EditProjectForm editProjectForm);

    void update(Long communityId, EditProjectForm projectForm) throws ProjectAccessDeniedException;
}
