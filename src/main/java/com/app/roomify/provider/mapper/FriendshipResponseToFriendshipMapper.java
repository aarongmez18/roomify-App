package com.app.roomify.provider.mapper;

import com.app.roomify.domain.Friendship;
import com.app.roomify.provider.exchange.response.FriendshipResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public interface FriendshipResponseToFriendshipMapper extends Converter<FriendshipResponse, Friendship> {
    Friendship convert(FriendshipResponse friendshipResponse);
}
