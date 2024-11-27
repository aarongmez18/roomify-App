package com.app.roomify.controller.mapper;

import com.app.roomify.domain.User;
import com.app.roomify.provider.exchange.response.UserResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserDomainToUserResponse extends Converter<User, UserResponse> {

    UserResponse convert(User user);
}
