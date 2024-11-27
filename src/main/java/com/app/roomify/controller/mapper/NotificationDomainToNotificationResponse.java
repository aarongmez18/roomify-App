package com.app.roomify.controller.mapper;

import com.app.roomify.domain.Notification;
import com.app.roomify.provider.exchange.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface NotificationDomainToNotificationResponse extends Converter<Notification, NotificationResponse> {

    NotificationResponse convert(Notification notification);
}
