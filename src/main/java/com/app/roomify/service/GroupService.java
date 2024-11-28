package com.app.roomify.service;

import com.app.roomify.domain.Group;
import com.app.roomify.domain.Media;

import java.util.List;

public interface GroupService {

    List<Media> getAllMedia();
    Media getMediaById(int id);
    Media saveMedia(Media media);
    void deleteMedia(int id);
    List<Group> getAllGroups();

    Group getGroupById(int id);

    Group saveGroup(Group group);

    void deleteGroup(int id);
}
