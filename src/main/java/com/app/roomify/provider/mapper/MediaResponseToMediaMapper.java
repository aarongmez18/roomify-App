package com.app.roomify.provider.mapper;

import com.app.roomify.domain.Media;
import com.app.roomify.provider.exchange.response.MediaResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MediaResponseToMediaMapper extends Converter<MediaResponse, Media> {
    Media convert(MediaResponse mediaResponse);
}
