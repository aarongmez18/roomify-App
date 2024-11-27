package com.app.roomify.domain;

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
    private String type; // Tipo de archivo (imagen, video, etc.)

    @Column(nullable = false)
    private int size; // Tamaño del archivo en bytes

    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "id", nullable = false)
    private User uploadedBy; // Usuario que subió el archivo

    @Column(nullable = true)
    private String associatedEntity; // Entidad asociada (User, Room, Group)

    @Column(nullable = true)
    private int associatedId; // ID de la entidad asociada
}
