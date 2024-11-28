package com.app.roomify.provider;

import com.app.roomify.domain.Group;
import java.util.List;

public interface RestGroupProvider {

    List<Group> getAllGroups();
    Group getGroupById(int id);
    Group saveGroup(Group group);
    void deleteGroup(int id);
}
