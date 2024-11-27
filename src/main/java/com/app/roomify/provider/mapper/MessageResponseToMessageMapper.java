package com.app.roomify.provider.mapper;

import com.app.roomify.domain.Message;
import com.app.roomify.provider.exchange.response.MessageResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MessageResponseToMessageMapper extends Converter<MessageResponse, Message> {
    Message convert(MessageResponse messageResponse);
}
