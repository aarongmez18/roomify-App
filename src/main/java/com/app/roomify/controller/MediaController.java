package com.app.roomify.controller;

import com.app.roomify.repository.domain.Media;
import com.app.roomify.controller.response.MediaResponse;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAllMedia() {
        try {
            List<Media> mediaList = mediaService.getAllMedia();
            List<MediaResponse> mediaResponses = mediaList.stream()
                    .map(media -> conversionService.convert(media, MediaResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(mediaResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaResponse> getMediaById(@PathVariable int id) {
        try {
            Media media = mediaService.getMediaById(id);
            MediaResponse mediaResponse = conversionService.convert(media, MediaResponse.class);
            return ResponseEntity.ok(mediaResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<MediaResponse> createMedia(@RequestBody MediaResponse mediaResponse) {
        try {
            Media media = conversionService.convert(mediaResponse, Media.class);
            media = mediaService.saveMedia(media);
            MediaResponse response = conversionService.convert(media, MediaResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable int id) {
        try {
            mediaService.deleteMedia(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
