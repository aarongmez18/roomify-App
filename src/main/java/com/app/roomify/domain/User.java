package com.app.roomify.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name; // Nombre del usuario

    @Column(nullable = false, unique = true)
    private String email; // Correo electrónico del usuario

    @Column(nullable = false)
    private String password; // Contraseña del usuario

    private String profilePictureURL; // URL de la foto de perfil

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // Fecha de creación

    private String bio; // Biografía del usuario

    private String location; // Ubicación del usuario

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status; // Estado del usuario

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> followers = new HashSet<>(); // Usuarios que siguen a este usuario

    @ManyToMany
    @JoinTable(
            name = "following",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<User> following = new HashSet<>(); // Usuarios que este usuario sigue

    public enum UserStatus {
        ACTIVE,
        INACTIVE,
        BANNED
    }
}
