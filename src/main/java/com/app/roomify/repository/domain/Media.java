package com.app.roomify.repository.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int size;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "id", nullable = false)
    private Member uploadedBy;

    @Column(name = "associated_entity",nullable = true)
    private String associatedEntity;

    @Column(name = "associated_id",nullable = true)
    private int associatedId;
}
