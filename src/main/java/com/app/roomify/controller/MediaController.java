package com.app.roomify.controller;

import com.app.roomify.domain.Media;
import com.app.roomify.provider.exchange.response.MediaResponse;
import com.app.roomify.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
        List<Media> mediaList = mediaService.getAllMedia();
        List<MediaResponse> mediaResponses = mediaList.stream()
                .map(media -> conversionService.convert(media, MediaResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mediaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaResponse> getMediaById(@PathVariable int id) {
        Media media = mediaService.getMediaById(id);
        MediaResponse mediaResponse = conversionService.convert(media, MediaResponse.class);
        return ResponseEntity.ok(mediaResponse);
    }

    @PostMapping
    public MediaResponse createMedia(@RequestBody MediaResponse mediaResponse) {
        Media media = conversionService.convert(mediaResponse, Media.class);
        media = mediaService.saveMedia(media);
        return conversionService.convert(media, MediaResponse.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable int id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}
