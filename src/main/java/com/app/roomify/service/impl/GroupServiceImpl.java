package com.app.roomify.service.impl;

import com.app.roomify.domain.Group;
import com.app.roomify.domain.Media;
import com.app.roomify.provider.exchange.repository.GroupRepository;
import com.app.roomify.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public List<Media> getAllMedia() {
        return List.of();
    }

    @Override
    public Media getMediaById(int id) {
        return null;
    }

    @Override
    public Media saveMedia(Media media) {
        return null;
    }

    @Override
    public void deleteMedia(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupById(int id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(int id) {
        groupRepository.deleteById(id);
    }
}
