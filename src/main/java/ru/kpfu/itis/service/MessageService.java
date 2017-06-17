package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Message;
import ru.kpfu.itis.service.root.CrudService;

/**
 * @author Vladislav Matyunin
 * Interface to work with messaging inside portal
 */
@Service
public interface MessageService extends CrudService<Message> {
}
