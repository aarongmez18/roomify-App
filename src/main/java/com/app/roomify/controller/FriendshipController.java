package com.app.roomify.controller;

import com.app.roomify.domain.Friendship;
import com.app.roomify.provider.exchange.response.FriendshipResponse;
import com.app.roomify.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
        List<Friendship> friendships = friendshipService.getAllFriendships();
        List<FriendshipResponse> friendshipResponses = friendships.stream()
                .map(friendship -> conversionService.convert(friendship, FriendshipResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(friendshipResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FriendshipResponse> getFriendshipById(@PathVariable int id) {
        Friendship friendship = friendshipService.getFriendshipById(id);
        FriendshipResponse friendshipResponse = conversionService.convert(friendship, FriendshipResponse.class);
        return ResponseEntity.ok(friendshipResponse);
    }

    @PostMapping
    public FriendshipResponse createFriendship(@RequestBody FriendshipResponse friendshipResponse) {
        Friendship friendship = conversionService.convert(friendshipResponse, Friendship.class);
        friendship = friendshipService.saveFriendship(friendship);
        return conversionService.convert(friendship, FriendshipResponse.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable int id) {
        friendshipService.deleteFriendship(id);
        return ResponseEntity.noContent().build();
    }
}
