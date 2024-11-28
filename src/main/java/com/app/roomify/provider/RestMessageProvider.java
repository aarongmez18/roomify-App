package com.app.roomify.provider;

import com.app.roomify.domain.Message;
import java.util.List;

public interface RestMessageProvider {

    List<Message> getAllMessages();
    Message getMessageById(int id);
    Message saveMessage(Message message);
    void deleteMessage(int id);
}
