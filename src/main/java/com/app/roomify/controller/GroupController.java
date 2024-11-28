package com.app.roomify.controller;

import com.app.roomify.domain.Group;
import com.app.roomify.provider.exchange.response.GroupResponse;
import com.app.roomify.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        List<GroupResponse> groupResponses = groups.stream()
                .map(group -> conversionService.convert(group, GroupResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(groupResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable int id) {
        Group group = groupService.getGroupById(id);
        GroupResponse groupResponse = conversionService.convert(group, GroupResponse.class);
        return ResponseEntity.ok(groupResponse);
    }

    @PostMapping
    public GroupResponse createGroup(@RequestBody GroupResponse groupResponse) {
        Group group = conversionService.convert(groupResponse, Group.class);
        group = groupService.saveGroup(group);
        return conversionService.convert(group, GroupResponse.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
