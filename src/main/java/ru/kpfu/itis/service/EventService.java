package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Event;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.dto.EventDto;
import ru.kpfu.itis.model.forms.EventForm;
import ru.kpfu.itis.service.root.CrudService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Vladislav_Matyunin
 *
 */
@Service
public interface EventService extends CrudService<Event> {
    /**
     * Pagination method, loads 10 events, sorted by date(the newest)
     * @param page - number of page
     * @return all Events on this page
     */
    List<Event> loadTopTen(int page);

    List<Event> loadEvents(int page, int num);

    void createEvent(EventForm eventForm, User user);

    @Transactional
    Event save(EventDto event);
    void subscribe(Long eventId);

    List<Event> findAllByUserId(Long id);
}
