package com.app.roomify.service;

import com.app.roomify.domain.Media;

import java.util.List;

public interface MediaService {

    List<Media> getAllMedia();
    Media getMediaById(int id);
    Media saveMedia(Media media);
    void deleteMedia(int id);
}
