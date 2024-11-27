package com.app.roomify.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendship")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id", nullable = false)
    private User requester; // Usuario que envía la solicitud

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    private User receiver;  // Usuario que recibe la solicitud

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;  // Estado de la relación

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de creación

    public enum Status {
        PENDING,
        ACCEPTED,
        BLOCKED
    }
}
