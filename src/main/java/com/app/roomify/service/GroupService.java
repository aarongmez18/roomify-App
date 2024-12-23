package com.app.roomify.service;

import com.app.roomify.repository.domain.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    List<Group> getAllGroups();

    Group getGroupById(int id);

    Group saveGroup(Group group);

    void deleteGroup(int id);
}
