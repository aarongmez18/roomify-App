package com.app.roomify.provider.exchange.response;

import com.app.roomify.domain.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponse {

    private int id;
    private String name; // Nombre del usuario
    private String email; // Correo electrónico del usuario
    private String password; // Contraseña del usuario
    private String profilePictureURL; // URL de la foto de perfil
    private LocalDateTime createdAt; // Fecha de creación
    private String bio; // Biografía del usuario
    private String location; // Ubicación del usuario
    private UserStatusResponse status; // Estado del usuario
    private Set<UserResponse> followers = new HashSet<>(); // Usuarios que siguen a este usuario
    private Set<UserResponse> following = new HashSet<>(); // Usuarios que este usuario sigue
    private Set<Room> rooms; // Salas en las que el usuario está presente

    public enum UserStatusResponse {
        ACTIVE,
        INACTIVE,
        BANNED
    }
}
