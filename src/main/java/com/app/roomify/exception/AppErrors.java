package com.app.roomify.exception;

import lombok.Getter;

@Getter
public enum AppErrors {

    ERROR_USER_NOT_FOUND("User not found with the given ID"),
    ERROR_ROOM_NOT_FOUND("Room not found with the given ID"),
    ERROR_PROFILE_NOT_FOUND("Profile not found with the given ID"),
    ERROR_MEDIA_NOT_FOUND("Media not found with the given ID"),
    ERROR_GROUP_NOT_FOUND("Group not found with the given ID"),
    ERROR_NOTIFICATION_NOT_FOUND("Notification not found with the given ID"),
    ERROR_MESSAGE_NOT_FOUND("Message not found with the given ID"),
    ERROR_FRIENDSHIP_NOT_FOUND("Friendship not found with the given ID"),
    ERROR_UNAUTHORIZED_ACTION("You are not authorized to perform this action"),
    ERROR_ROOM_CAPACITY_EXCEEDED("The room has reached its maximum capacity"),
    ERROR_INVALID_ROOM_ACCESS("You do not have permission to access this room"),
    ERROR_USER_ALREADY_EXISTS("A user with the same email already exists"),
    ERROR_ROOM_EXPIRED("The room has expired and is no longer accessible"),
    ERROR_FILE_UPLOAD_FAILED("Failed to upload the file"),
    ERROR_INVALID_REQUEST("The request is invalid or malformed"),
    ERROR_DATABASE_CONFLICT("A conflict occurred while interacting with the database"),
    ERROR_INTERNAL_SERVER("An unexpected error occurred, please try again later"),
    ERROR_INVALID_CREDENTIALS("The provided credentials are incorrect"),
    ERROR_ACCOUNT_INACTIVE("Your account is currently inactive or banned"),
    ERROR_FOLLOW_ALREADY_EXISTS("You are already following this user"),
    ERROR_UNFOLLOW_NOT_ALLOWED("You cannot unfollow a user you are not following"),
    ERROR_NOTIFICATION_ALREADY_READ("The notification has already been marked as read");


    private final String message;

    AppErrors(String message) {
        this.message = message;
    }
}
