package com.app.roomify.service;

import com.app.roomify.repository.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Message> getAllMessages();
    Message getMessageById(int id);
    Message saveMessage(Message message);
    void deleteMessage(int id);
}
