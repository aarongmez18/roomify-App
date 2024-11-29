package com.app.roomify.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {

    private int id;
    private String name; // Nombre del grupo
    private String description; // Descripción del grupo
    private MemberResponse owner; // Propietario del grupo
    private Set<MemberResponse> members; // Miembros del grupo
    private LocalDateTime createdAt; // Fecha de creación
}
