package com.app.roomify.provider.mapper;


import com.app.roomify.domain.Group;
import com.app.roomify.provider.exchange.response.GroupResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface GroupResponseToGroupMapper extends Converter<GroupResponse, Group> {
    Group convert(GroupResponse groupResponse);
}
