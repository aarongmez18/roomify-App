package com.app.roomify.controller;

import com.app.roomify.domain.Message;
import com.app.roomify.provider.exchange.response.MessageResponse;
import com.app.roomify.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        List<MessageResponse> messageResponses = messages.stream()
                .map(message -> conversionService.convert(message, MessageResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable int id) {
        Message message = messageService.getMessageById(id);
        MessageResponse messageResponse = conversionService.convert(message, MessageResponse.class);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping
    public MessageResponse createMessage(@RequestBody MessageResponse messageResponse) {
        Message message = conversionService.convert(messageResponse, Message.class);
        message = messageService.saveMessage(message);
        return conversionService.convert(message, MessageResponse.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
