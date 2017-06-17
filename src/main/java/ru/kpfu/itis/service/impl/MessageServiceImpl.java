package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Message;
import ru.kpfu.itis.repository.MessageRepository;
import ru.kpfu.itis.service.MessageService;
import ru.kpfu.itis.service.root.AbstractCrudService;

/**
 * Created by vladislav on 30.04.17.
 */
@Service
public class MessageServiceImpl extends AbstractCrudService<Message> implements MessageService{
    @Autowired
    private
    MessageRepository messageRepository;
    @Override
    protected void setRepository() {
        repository = messageRepository;
    }
}
