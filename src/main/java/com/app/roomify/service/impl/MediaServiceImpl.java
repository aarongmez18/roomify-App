package com.app.roomify.service.impl;

import com.app.roomify.domain.Media;
import com.app.roomify.provider.RestMediaProvider;
import com.app.roomify.provider.exchange.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements RestMediaProvider {
    private final MediaRepository mediaRepository;

    @Override
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public Media getMediaById(int id) {
        return mediaRepository.findById(id).orElseThrow(() -> new RuntimeException("Media not found"));
    }

    @Override
    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }

    @Override
    public void deleteMedia(int id) {
        mediaRepository.deleteById(id);
    }
}
