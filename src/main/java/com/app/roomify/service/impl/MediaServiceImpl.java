package com.app.roomify.service.impl;

import com.app.roomify.repository.domain.Media;
import com.app.roomify.repository.MediaRepository;
import com.app.roomify.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
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
