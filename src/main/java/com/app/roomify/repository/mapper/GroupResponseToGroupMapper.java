package com.app.roomify.repository.mapper;


import com.app.roomify.repository.domain.Group;
import com.app.roomify.controller.response.GroupResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface GroupResponseToGroupMapper extends Converter<GroupResponse, Group> {
    Group convert(GroupResponse groupResponse);
}
