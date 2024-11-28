package com.app.roomify.domain;

import com.app.roomify.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

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
    private UserStatus userStatus; // Estado del usuario

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

    @ManyToMany
    @JoinTable(
            name = "room_users", // Tabla intermedia
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Room> rooms; // Salas en las que el usuario está presente

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
