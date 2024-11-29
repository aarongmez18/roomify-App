package com.app.roomify.controller.mapper;

import com.app.roomify.repository.domain.Notification;
import com.app.roomify.controller.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface NotificationDomainToNotificationResponse extends Converter<Notification, NotificationResponse> {

    NotificationResponse convert(Notification notification);
}
