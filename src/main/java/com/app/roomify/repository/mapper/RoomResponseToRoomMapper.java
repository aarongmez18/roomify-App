package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Room;
import com.app.roomify.controller.response.RoomResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface RoomResponseToRoomMapper extends Converter<RoomResponse, Room> {
    Room convert(RoomResponse roomResponse);
}
