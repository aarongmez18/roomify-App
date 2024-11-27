package com.app.roomify.controller.mapper;


import com.app.roomify.domain.Media;
import com.app.roomify.provider.exchange.response.MediaResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MediaDomainToMediaResponse extends Converter<Media, MediaResponse> {

    MediaResponse convert(Media media);
}
