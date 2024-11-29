package com.app.roomify.controller.mapper;


import com.app.roomify.repository.domain.Group;
import com.app.roomify.controller.response.GroupResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface GroupDomainToGroupResponse extends Converter<Group, GroupResponse> {

    GroupResponse convert(Group group);
}
