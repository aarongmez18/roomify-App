package com.app.roomify.service;

import com.app.roomify.domain.Friendship;

import java.util.List;

public interface FriendshipService {

    List<Friendship> getAllFriendships();
    Friendship getFriendshipById(int id);
    Friendship saveFriendship(Friendship friendship);
    void deleteFriendship(int id);
}
