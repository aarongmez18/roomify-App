package com.app.roomify.service;

import com.app.roomify.domain.Media;

import java.util.List;

public interface MediaService {

    List<Media> getAllMedia();
    Media getMediaById(Long id);
    Media saveMedia(Media media);
    void deleteMedia(Long id);
}
