package com.app.roomify.repository.domain;

import com.app.roomify.repository.domain.enums.MemberStatus;
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
public class Member implements UserDetails {

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
    private LocalDateTime createdAt;

    private String bio;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private MemberStatus memberStatus;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<Member> followers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "following",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private Set<Member> following = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "room_users", // Tabla intermedia
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Room> rooms;

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
