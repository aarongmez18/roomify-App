package com.app.roomify.controller;

import com.app.roomify.domain.Notification;
import com.app.roomify.provider.exchange.response.NotificationResponse;
import com.app.roomify.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        List<NotificationResponse> notificationResponses = notifications.stream()
                .map(notification -> conversionService.convert(notification, NotificationResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable int id) {
        Notification notification = notificationService.getNotificationById(id);
        NotificationResponse notificationResponse = conversionService.convert(notification, NotificationResponse.class);
        return ResponseEntity.ok(notificationResponse);
    }

    @PostMapping
    public NotificationResponse createNotification(@RequestBody NotificationResponse notificationResponse) {
        Notification notification = conversionService.convert(notificationResponse, Notification.class);
        notification = notificationService.saveNotification(notification);
        return conversionService.convert(notification, NotificationResponse.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
