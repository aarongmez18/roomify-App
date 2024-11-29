package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Member;
import com.app.roomify.controller.response.MemberResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserResponseToUserMapper extends Converter<MemberResponse, Member> {
    Member convert(MemberResponse memberResponse);
}
