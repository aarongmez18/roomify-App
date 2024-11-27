package com.app.roomify.provider.exchange.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {

    private int id;
    private String name; // Nombre del grupo
    private String description; // Descripción del grupo
    private UserResponse owner; // Propietario del grupo
    private Set<UserResponse> members; // Miembros del grupo
    private LocalDateTime createdAt; // Fecha de creación
}
