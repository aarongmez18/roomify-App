package com.app.roomify.provider.exchange.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipResponse {

    private int id;
    private UserResponse requester; // Usuario que envía la solicitud
    private UserResponse receiver;  // Usuario que recibe la solicitud
    private Status status;  // Estado de la relación
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de creación

    public enum Status {
        PENDING,
        ACCEPTED,
        BLOCKED
    }
}
