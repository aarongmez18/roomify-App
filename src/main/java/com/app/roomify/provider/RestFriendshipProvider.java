package com.app.roomify.provider;

import com.app.roomify.domain.Friendship;
import java.util.List;

public interface RestFriendshipProvider {

    List<Friendship> getAllFriendships();
    Friendship getFriendshipById(int id);
    Friendship saveFriendship(Friendship friendship);
    void deleteFriendship(int id);
}
