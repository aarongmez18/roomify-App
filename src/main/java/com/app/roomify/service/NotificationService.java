package com.app.roomify.service;

import com.app.roomify.domain.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    List<Notification> getAllNotifications();
    Notification getNotificationById(int id);
    Notification saveNotification(Notification notification);
    void deleteNotification(int id);
}
