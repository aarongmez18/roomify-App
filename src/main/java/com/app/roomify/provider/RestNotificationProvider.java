package com.app.roomify.provider;

import com.app.roomify.domain.Notification;
import java.util.List;

public interface RestNotificationProvider {

    List<Notification> getAllNotifications();
    Notification getNotificationById(int id);
    Notification saveNotification(Notification notification);
    void deleteNotification(int id);
}
