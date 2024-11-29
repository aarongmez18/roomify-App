package com.app.roomify.service;

import com.app.roomify.repository.domain.Friendship;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendshipService {

    List<Friendship> getAllFriendships();
    Friendship getFriendshipById(int id);
    Friendship saveFriendship(Friendship friendship);
    void deleteFriendship(int id);
}
