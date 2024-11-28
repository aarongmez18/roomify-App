package com.app.roomify.service;

import com.app.roomify.domain.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();
    Message getMessageById(int id);
    Message saveMessage(Message message);
    void deleteMessage(int id);
}
