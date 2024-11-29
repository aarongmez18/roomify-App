package com.app.roomify.service.impl;

import com.app.roomify.repository.domain.Friendship;
import com.app.roomify.repository.FriendshipRepository;
import com.app.roomify.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;

    @Override
    public List<Friendship> getAllFriendships() {
        return friendshipRepository.findAll();
    }

    @Override
    public Friendship getFriendshipById(int id) {
        return friendshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Friendship not found"));
    }

    @Override
    public Friendship saveFriendship(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @Override
    public void deleteFriendship(int id) {
        friendshipRepository.deleteById(id);
    }

}
