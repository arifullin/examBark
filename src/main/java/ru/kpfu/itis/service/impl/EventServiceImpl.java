package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.EventDto;
import ru.kpfu.itis.model.forms.EventForm;
import ru.kpfu.itis.repository.EventRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.EventService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.root.AbstractCrudService;
import ru.kpfu.itis.service.security.MyUserDetail;
import ru.kpfu.itis.utils.UploadUtils;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class EventServiceImpl extends AbstractCrudService<Event> implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    StorageService eventImageStorageService;

    @Override
    protected void setRepository() {
        repository = eventRepository;
    }

    @Override
    public List<Event> loadTopTen(int page) {
        return eventRepository.findAllOrOrderByDateStarted(new PageRequest(page, 10)).getContent();
    }

    @Override
    public List<Event> loadEvents(int page, int num) {
        return eventRepository.findAll(new PageRequest(page, num)).getContent();
    }



    @Override
    public void createEvent(EventForm eventForm, User user) {
        Event event = new Event();
        event.setTitle(eventForm.getTitle());
        event.setDescription(eventForm.getDescription());

        if (!eventForm.getAvatar().isEmpty()) {
            String uploadUrl1 = eventImageStorageService.store(UploadUtils.EVENTS_PATH, eventForm.getUploadUrl1());
            String uploadUrl2 = eventImageStorageService.store(UploadUtils.EVENTS_PATH, eventForm.getUploadUrl2());
            String uploadUrl3 = eventImageStorageService.store(UploadUtils.EVENTS_PATH, eventForm.getUploadUrl3());
            event.setUploadUrl1(uploadUrl1);
            event.setUploadUrl1(uploadUrl2);
            event.setUploadUrl1(uploadUrl3);
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        event.setAuthor(user);
        //  event.setUsers(users);
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event save(EventDto eventDto) {

        Event event = new Event();
        event.setAuthor(eventDto.getAuthor());
        event.setDescription(eventDto.getDescription());
        event.setTitle(eventDto.getTitle());
        event.setDateStarted(getDateStarted(eventDto.getDate(), eventDto.getHours(), eventDto.getMinutes()));
        event.setDatePublished(LocalDateTime.now(ZoneId.of("UTC+3")));
        event.setAddress(eventDto.getAddress());
        event.setUsers(createNewUserList(eventDto.getAuthor()));
        MultipartFile[] files = eventDto.getFiles();

        if (files != null) {
            if (files.length > 0 && files[0] != null && files[0].getSize() != 0)
                event.setUploadUrl1(storageService.store(UploadUtils.EVENTS_PATH, files[0]));
            if (files.length > 1 && files[1] != null && files[1].getSize() != 0)
                event.setUploadUrl2(storageService.store(UploadUtils.EVENTS_PATH, files[1]));
            if (files.length > 2 && files[2] != null && files[2].getSize() != 0)
                event.setUploadUrl3(storageService.store(UploadUtils.EVENTS_PATH, files[2]));
        }
        return eventRepository.save(event);

    }

    private Set<User> createNewUserList(User user) {
        Set<User> users = new HashSet<>();
        users.add(user);
        return users;
    }

    private LocalDateTime getDateStarted(String date, String hours, String minutes) {
        String[] partsOfDate = date.split("-");
        return LocalDateTime.of(Integer.parseInt(partsOfDate[0]),
                Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]),
                Integer.parseInt(hours), Integer.parseInt(minutes));
    }


    @Override
    @Transactional
    public void subscribe(Long eventId) {
        Event event = getById(eventId);
        User currentUser = ((MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User userFromDatabse = userRepository.findByEmail(currentUser.getEmail());
        if (event.getUsers().contains(userFromDatabse)) {
            event.getUsers().remove(userFromDatabse);
            userFromDatabse.getSubscribedEvents().remove(event);
        } else {
            event.getUsers().add(userFromDatabse);
            userFromDatabse.getSubscribedEvents().add(event);
        }
        update(event);
        userRepository.save(userFromDatabse);
    }

    @Override
    public List<Event> findAllByUserId(Long id) {
        return eventRepository.findAllByUser(id);
    }

    private Date stringTodate(String dateStarted) {
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateStarted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        return date;
    }

}
