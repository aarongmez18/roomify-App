package com.app.roomify.service;

import com.app.roomify.repository.domain.Media;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MediaService {

    List<Media> getAllMedia();
    Media getMediaById(int id);
    Media saveMedia(Media media);
    void deleteMedia(int id);
}
