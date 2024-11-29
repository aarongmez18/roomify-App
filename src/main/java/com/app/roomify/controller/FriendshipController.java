package com.app.roomify.controller;

import com.app.roomify.repository.domain.Friendship;
import com.app.roomify.controller.response.FriendshipResponse;
import com.app.roomify.service.FriendshipService;
import com.app.roomify.exception.RoomifyException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<FriendshipResponse>> getAllFriendships() {
        try {
            List<Friendship> friendships = friendshipService.getAllFriendships();
            List<FriendshipResponse> friendshipResponses = friendships.stream()
                    .map(friendship -> conversionService.convert(friendship, FriendshipResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(friendshipResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendshipResponse> getFriendshipById(@PathVariable int id) {
        try {
            Friendship friendship = friendshipService.getFriendshipById(id);
            FriendshipResponse friendshipResponse = conversionService.convert(friendship, FriendshipResponse.class);
            return ResponseEntity.ok(friendshipResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<FriendshipResponse> createFriendship(@RequestBody FriendshipResponse friendshipResponse) {
        try {
            Friendship friendship = conversionService.convert(friendshipResponse, Friendship.class);
            friendship = friendshipService.saveFriendship(friendship);
            FriendshipResponse response = conversionService.convert(friendship, FriendshipResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable int id) {
        try {
            friendshipService.deleteFriendship(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
