package com.app.roomify.controller.mapper;


import com.app.roomify.repository.domain.Message;
import com.app.roomify.controller.response.MessageResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface MessageDomainToMessageResponse extends Converter<Message, MessageResponse> {

    MessageResponse convert(Message message);
}
