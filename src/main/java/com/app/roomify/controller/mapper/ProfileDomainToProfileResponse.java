package com.app.roomify.controller.mapper;

import com.app.roomify.repository.domain.Profile;
import com.app.roomify.controller.response.ProfileResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ProfileDomainToProfileResponse extends Converter<Profile, ProfileResponse> {

    ProfileResponse convert(Profile profile);
}
