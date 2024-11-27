package com.app.roomify.provider.exchange.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponse {

    private int id;
    private String type; // Tipo de archivo (imagen, video, etc.)
    private int size; // Tamaño del archivo en bytes
    private UserResponse uploadedBy; // Usuario que subió el archivo
    private String associatedEntity; // Entidad asociada (User, Room, Group)
    private int associatedId; // ID de la entidad asociada
}
