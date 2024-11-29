package com.app.roomify.controller.response;

import com.app.roomify.repository.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

    private int id;
    private String content; // Contenido del mensaje
    private Room room; // Sala asociada al mensaje
    private MemberResponse user; // Usuario que envía el mensaje
    private LocalDateTime sentAt; // Fecha de envío del mensaje
}
