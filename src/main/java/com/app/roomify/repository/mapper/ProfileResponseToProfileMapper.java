package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Profile;
import com.app.roomify.controller.response.ProfileResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ProfileResponseToProfileMapper extends Converter<ProfileResponse, Profile> {
    Profile convert(ProfileResponse profileResponse);
}
