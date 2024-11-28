package com.app.roomify.service;

import com.app.roomify.domain.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getAllNotifications();
    Notification getNotificationById(int id);
    Notification saveNotification(Notification notification);
    void deleteNotification(int id);
}
