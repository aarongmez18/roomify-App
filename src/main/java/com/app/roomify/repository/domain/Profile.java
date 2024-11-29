package com.app.roomify.repository.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String bio;

    @Column(name = "profile_picture_url", nullable = false)
    private String profilePictureUrl;
}
