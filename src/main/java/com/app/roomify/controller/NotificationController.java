package com.app.roomify.controller;

import com.app.roomify.exception.AppErrors;
import com.app.roomify.repository.domain.Notification;
import com.app.roomify.controller.response.NotificationResponse;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final ConversionService conversionService;

    /**
     * Obtener todas las notificaciones.
     *
     * @return Lista de todas las notificaciones.
     */
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        try {
            List<Notification> notifications = notificationService.getAllNotifications();
            List<NotificationResponse> notificationResponses = notifications.stream()
                    .map(notification -> conversionService.convert(notification, NotificationResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(notificationResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener una notificación por su ID.
     *
     * @param id ID de la notificación.
     * @return Detalles de la notificación.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable int id) {
        try {
            Notification notification = notificationService.getNotificationById(id);
            NotificationResponse notificationResponse = conversionService.convert(notification, NotificationResponse.class);
            return ResponseEntity.ok(notificationResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(AppErrors.ERROR_NOTIFICATION_NOT_FOUND.ordinal()).body(null);
        }
    }

    /**
     * Crear una nueva notificación.
     *
     * @param notificationResponse Detalles de la notificación a crear.
     * @return Detalles de la notificación creada.
     */
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@Valid @RequestBody NotificationResponse notificationResponse) {
        try {
            Notification notification = conversionService.convert(notificationResponse, Notification.class);
            notification = notificationService.saveNotification(notification);
            NotificationResponse response = conversionService.convert(notification, NotificationResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Eliminar una notificación por su ID.
     *
     * @param id ID de la notificación a eliminar.
     * @return Respuesta vacía en caso de éxito.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        try {
            notificationService.deleteNotification(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
