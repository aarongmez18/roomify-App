package com.app.roomify.controller;

import com.app.roomify.repository.domain.Message;
import com.app.roomify.controller.response.MessageResponse;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final ConversionService conversionService;

    /**
     * Obtener todos los mensajes.
     *
     * @return Lista de todos los mensajes.
     */
    @GetMapping
    public ResponseEntity<List<MessageResponse>> getAllMessages() {
        try {
            List<Message> messages = messageService.getAllMessages();
            List<MessageResponse> messageResponses = messages.stream()
                    .map(message -> conversionService.convert(message, MessageResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(messageResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener un mensaje por su ID.
     *
     * @param id ID del mensaje.
     * @return Detalles del mensaje.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable int id) {
        try {
            Message message = messageService.getMessageById(id);
            MessageResponse messageResponse = conversionService.convert(message, MessageResponse.class);
            return ResponseEntity.ok(messageResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Crear un nuevo mensaje.
     *
     * @param messageResponse Detalles del mensaje a crear.
     * @return Detalles del mensaje creado.
     */
    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@Valid @RequestBody MessageResponse messageResponse) {
        try {
            Message message = conversionService.convert(messageResponse, Message.class);
            message = messageService.saveMessage(message);
            MessageResponse response = conversionService.convert(message, MessageResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Eliminar un mensaje por su ID.
     *
     * @param id ID del mensaje a eliminar.
     * @return Respuesta vacía en caso de éxito.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        try {
            messageService.deleteMessage(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
