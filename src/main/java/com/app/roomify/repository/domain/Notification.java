package com.app.roomify.repository.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Member member; // Usuario que recibe la notificación

    @Column(nullable = false)
    private String message; // Mensaje de la notificación

    @Column(name = "reader", nullable = false)
    private boolean read; // Indica si la notificación ha sido leída

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de creación
}
