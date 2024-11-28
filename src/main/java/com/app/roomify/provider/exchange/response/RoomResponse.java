package com.app.roomify.provider.exchange.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

    private int id;
    private String name; // Nombre de la sala
    private String description; // Descripción de la sala
    private Double latitude; // Latitud de la sala
    private Double longitude; // Longitud de la sala
    private LocalDateTime createdAt; // Fecha de creación
    private UserResponse owner; // Propietario de la sala
    private Integer maxCapacity; // Número máximo de usuarios permitido
    private Double price; // Precio de acceso a la sala
    private boolean isPublic; // Define si la sala es pública o privada
    private Date expirationDate; // Fecha de expiración de la sala
    private MediaResponse profilePhoto; // Foto de perfil de la sala
    private List<UserResponse> users; // Lista de usuarios en la sala
}
