package com.app.roomify.provider.mapper;

import com.app.roomify.domain.Profile;
import com.app.roomify.provider.exchange.response.ProfileResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ProfileResponseToProfileMapper extends Converter<ProfileResponse, Profile> {
    Profile convert(ProfileResponse profileResponse);
}
