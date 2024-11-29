package com.app.roomify.repository.mapper;

import com.app.roomify.repository.domain.Notification;
import com.app.roomify.controller.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface NotificationResponseToNotificationMapper extends Converter<NotificationResponse, Notification> {
    Notification convert(NotificationResponse notificationResponse);
}
