package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Community;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.News;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.EventDto;
import ru.kpfu.itis.model.enums.CommunityType;
import ru.kpfu.itis.model.enums.Privileges;
import ru.kpfu.itis.model.forms.CreateCommunityForm;
import ru.kpfu.itis.model.forms.EditProjectForm;
import ru.kpfu.itis.model.forms.ProjectEventForm;
import ru.kpfu.itis.repository.CommunityRepository;
import ru.kpfu.itis.repository.NewsRepository;
import ru.kpfu.itis.service.CommunityService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.exception.ProjectAccessDeniedException;
import ru.kpfu.itis.service.root.AbstractCrudService;
import ru.kpfu.itis.utils.UploadUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class CommunityServiceImpl extends AbstractCrudService<Community> implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private StorageService imageStorageService;
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsRepository newsRepository;

    @Override
    protected void setRepository() {
        repository = communityRepository;
    }

    //TODO: check and test, may be Contains...
    @Override
    public List<Community> findByUser(User user) {
        return communityRepository.findByUsersContaining(user);
    }

    @Override
    public List<Community> getGroups(int page, int num) {
        return communityRepository.findAll(new PageRequest(page, num, new Sort("id"))).getContent();
    }

    @Override
    public void subscribeUser(Long communityId, User user) {
        Community community = getById(communityId);
        if (!community.getUsers().contains(user)) {
            community.getUsers().add(user);
            update(community);
        }
    }

    @Override
    public void unSubscribeUser(Long communityId, User user) {
        Community community = getById(communityId);
        community.getUsers().remove(user);
        update(community);
    }

    @Override
    public boolean isSubscribed(Long projectId, User user) {
        Community community = communityRepository.getOne(projectId);
        //TODO: fix that shit
        return community.getUsers().contains(user) || community.getAdmins().contains(user) || community.getLeader().getId().equals(user.getId());
    }

    public Privileges checkPrivileges(Community community) throws ProjectAccessDeniedException {
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        if (community.getLeader().getId().equals(currentUser.getId())) return Privileges.LEADER;
        for (User user : community.getAdmins()) {
            if (user.getId().equals(currentUser.getId())) return Privileges.ADMIN;
        }
        for (User user : community.getUsers()) {
            if (user.getId().equals(currentUser.getId())) return Privileges.USER;
        }
        throw new ProjectAccessDeniedException();
    }


    @Override
    public Event createEvent(Long communityId, ProjectEventForm event) throws ProjectAccessDeniedException {
        Community community = getById(communityId);
        Privileges privileges = checkPrivileges(community);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        EventDto eventDto = event.eventDto();
        User currentUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        eventDto.setAuthor(currentUser);
        Event createdEvent = eventService.save(eventDto);
        community.getEvents().add(createdEvent);
        update(community);
        return createdEvent;
    }

    @Override
    public News createPost(Long communityId, News post) throws ProjectAccessDeniedException {
        Community community = getById(communityId);
        Privileges privileges = checkPrivileges(community);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        News news = newsRepository.save(post);
        community.getNews().add(news);
        update(community);
        return news;
    }

    /*  private Event createEvent(CreateNewsOrEventForm form, User user) {
          EventDto eventDto = new EventDto(form.getTitle(), form.getTags(),
                  form.getDescription(), form.getAddress(), form.getDate(),
                  form.getHours(), form)

      }
  */
    public Community findByName(String name) {
        return communityRepository.findByName(name);
    }

    public void createCommunity(CreateCommunityForm communityForm, User user) {
        Community community = new Community();
        community.setDateCreated(new Date(System.currentTimeMillis()));
        community.setName(communityForm.getName());
        community.setCommunityType(CommunityType.PUBLIC);
        if (!communityForm.getAvatar().isEmpty()) {
            String avatarUrl = imageStorageService.store(UploadUtils.COMMUNITIES_PATH, communityForm.getAvatar());
            community.setAvatarUrl(avatarUrl);
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        List<User> admins = new ArrayList<>();
        admins.add(user);
        community.setAdmins(admins);
        community.setLeader(user);
        community.setUsers(users);
        communityRepository.save(community);
    }

    @Override
    public void update(Long projectId, EditProjectForm communityForm) throws ProjectAccessDeniedException {
        Community community = getById(projectId);
        Privileges privileges = checkPrivileges(community);
        if (privileges.equals(Privileges.USER)) throw new ProjectAccessDeniedException();
        community.setDescription(communityForm.getDescription());
        community.setName(communityForm.getName());
        if (communityForm.getAvatar() != null && !communityForm.getAvatar().isEmpty())
            community.setAvatarUrl(imageStorageService.store(UploadUtils.COMMUNITIES_PATH, communityForm.getAvatar()));
        update(community);
    }

}
