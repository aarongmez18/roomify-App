package com.app.roomify.provider.mapper;

import com.app.roomify.domain.Room;
import com.app.roomify.provider.exchange.response.RoomResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface RoomResponseToRoomMapper extends Converter<RoomResponse, Room> {
    Room convert(RoomResponse roomResponse);
}
