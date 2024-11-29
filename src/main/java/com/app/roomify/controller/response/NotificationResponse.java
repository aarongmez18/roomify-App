package com.app.roomify.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private int id;
    private MemberResponse user; // Usuario que recibe la notificación
    private String message; // Mensaje de la notificación
    private boolean read; // Indica si la notificación ha sido leída
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de creación
}
