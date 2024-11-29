package com.app.roomify.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private int id;
    private MemberResponse user; // Usuario asociado al perfil
    private String bio; // Biografía del usuario
    private String profilePictureUrl; // URL de la foto de perfil
}
