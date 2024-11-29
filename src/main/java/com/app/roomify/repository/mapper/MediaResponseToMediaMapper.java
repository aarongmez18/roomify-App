package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Media;
import com.app.roomify.controller.response.MediaResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MediaResponseToMediaMapper extends Converter<MediaResponse, Media> {
    Media convert(MediaResponse mediaResponse);
}
