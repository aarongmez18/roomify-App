package com.app.roomify.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name; // Nombre de la sala

    @Column(nullable = true)
    private String description; // Descripción de la sala

    @Column(nullable = false)
    private Double latitude; // Latitud de la sala

    @Column(nullable = false)
    private Double longitude; // Longitud de la sala

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // Fecha de creación

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner; // Propietario de la sala

    @Column(nullable = false)
    private Integer maxCapacity; // Número máximo de usuarios permitido

    @Column(nullable = false)
    private Double price; // Precio de acceso a la sala

    @Column(nullable = false)
    private boolean isPublic; // Define si la sala es pública o privada

    @Column(nullable = true)
    private Date expirationDate; // Fecha de expiración de la sala

    @ManyToOne
    @JoinColumn(name = "profile_photo_id", referencedColumnName = "id")
    private Media profilePhoto; // Foto de perfil de la sala
}
