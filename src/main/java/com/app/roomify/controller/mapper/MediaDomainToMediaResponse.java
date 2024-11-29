package com.app.roomify.controller.mapper;


import com.app.roomify.repository.domain.Media;
import com.app.roomify.controller.response.MediaResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MediaDomainToMediaResponse extends Converter<Media, MediaResponse> {

    MediaResponse convert(Media media);
}
