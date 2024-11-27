package com.app.roomify.provider.mapper;

import com.app.roomify.domain.Notification;
import com.app.roomify.provider.exchange.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface NotificationResponseToNotificationMapper extends Converter<NotificationResponse, Notification> {
    Notification convert(NotificationResponse notificationResponse);
}
