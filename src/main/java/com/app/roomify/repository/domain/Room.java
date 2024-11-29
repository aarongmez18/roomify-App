package com.app.roomify.repository.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

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
    private Member owner; // Propietario de la sala

    @Column(name = "max_capacity",nullable = false)
    private Integer maxCapacity; // Número máximo de usuarios permitido

    @Column(nullable = false)
    private Double price; // Precio de acceso a la sala

    @Column(name = "is_public",nullable = false)
    private boolean isPublic; // Define si la sala es pública o privada

    @Column(name = "expiration_date",nullable = true)
    private Date expirationDate; // Fecha de expiración de la sala

    @ManyToOne
    @JoinColumn(name = "profile_photo_id", referencedColumnName = "id")
    private Media profilePhoto; // Foto de perfil de la sala

    // Relación ManyToMany con User
    @ManyToMany
    @JoinTable(
            name = "room_users",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Member> members; // Relación con los usuarios en la sala
}
