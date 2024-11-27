package com.app.roomify.provider.mapper;

import com.app.roomify.domain.User;
import com.app.roomify.provider.exchange.response.UserResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserResponseToUserMapper extends Converter<UserResponse, User> {
    User convert(UserResponse userResponse);
}
