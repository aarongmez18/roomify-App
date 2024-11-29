package com.app.roomify.controller;

import com.app.roomify.repository.domain.Group;
import com.app.roomify.controller.response.GroupResponse;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
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
        try {
            List<Group> groups = groupService.getAllGroups();
            List<GroupResponse> groupResponses = groups.stream()
                    .map(group -> conversionService.convert(group, GroupResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(groupResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable int id) {
        try {
            Group group = groupService.getGroupById(id);
            GroupResponse groupResponse = conversionService.convert(group, GroupResponse.class);
            return ResponseEntity.ok(groupResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupResponse groupResponse) {
        try {
            Group group = conversionService.convert(groupResponse, Group.class);
            group = groupService.saveGroup(group);
            GroupResponse response = conversionService.convert(group, GroupResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
