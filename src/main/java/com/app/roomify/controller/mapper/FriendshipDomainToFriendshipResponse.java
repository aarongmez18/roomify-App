package com.app.roomify.controller.mapper;


import com.app.roomify.repository.domain.Friendship;
import com.app.roomify.controller.response.FriendshipResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface FriendshipDomainToFriendshipResponse extends Converter<Friendship, FriendshipResponse> {

    FriendshipResponse convert(Friendship friendship);
}
