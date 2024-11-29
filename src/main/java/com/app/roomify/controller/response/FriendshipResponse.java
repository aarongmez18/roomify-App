package com.app.roomify.controller.response;

import com.app.roomify.repository.domain.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipResponse {

    private int id;
    private MemberResponse requester; // Usuario que envía la solicitud
    private MemberResponse receiver;  // Usuario que recibe la solicitud
    private RequestStatus status;  // Estado de la relación
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de creación

}
