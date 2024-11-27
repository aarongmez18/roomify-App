package com.app.roomify.controller.mapper;

import com.app.roomify.domain.Room;
import com.app.roomify.provider.exchange.response.RoomResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface RoomDomainToRoomResponse extends Converter<Room, RoomResponse> {

    RoomResponse convert(Room room);
}
