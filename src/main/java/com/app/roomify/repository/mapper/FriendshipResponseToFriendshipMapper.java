package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Friendship;
import com.app.roomify.controller.response.FriendshipResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public interface FriendshipResponseToFriendshipMapper extends Converter<FriendshipResponse, Friendship> {
    Friendship convert(FriendshipResponse friendshipResponse);
}
