package com.app.roomify.controller.mapper;

import com.app.roomify.repository.domain.Member;
import com.app.roomify.controller.response.MemberResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface UserDomainToUserResponse extends Converter<Member, MemberResponse> {

    MemberResponse convert(Member member);
}
