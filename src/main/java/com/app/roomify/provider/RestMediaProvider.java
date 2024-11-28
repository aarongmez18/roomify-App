package com.app.roomify.provider;

import com.app.roomify.domain.Media;
import java.util.List;

public interface RestMediaProvider {

    List<Media> getAllMedia();
    Media getMediaById(int id);
    Media saveMedia(Media media);
    void deleteMedia(int id);
}
