package com.app.roomify.service.impl;

import com.app.roomify.domain.Friendship;
import com.app.roomify.provider.RestFriendshipProvider;
import com.app.roomify.provider.exchange.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements RestFriendshipProvider {

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
