package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Message;
import com.app.roomify.controller.response.MessageResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MessageResponseToMessageMapper extends Converter<MessageResponse, Message> {
    Message convert(MessageResponse messageResponse);
}
