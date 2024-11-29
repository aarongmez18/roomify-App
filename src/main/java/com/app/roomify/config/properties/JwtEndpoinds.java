package com.app.roomify.config.properties;

public enum JwtEndpoinds {

    // Endpoints relacionados con usuarios
    GET_ALL_USERS("/api/users"),
    GET_USER_BY_ID("/api/users/{id}"),
    CREATE_USER("/api/users"),
    UPDATE_USER("/api/users/{id}"),
    DELETE_USER("/api/users/{id}"),
    SEARCH_USERS("/api/users/search"),
    FOLLOW_USER("/api/users/{followerId}/follow/{followeeId}"),
    UNFOLLOW_USER("/api/users/{followerId}/unfollow/{followeeId}"),
    GET_USER_FOLLOWERS("/api/users/{id}/followers"),
    GET_USER_FOLLOWING("/api/users/{id}/following"),
    ACTIVATE_USER("/api/users/{id}/activate"),
    DEACTIVATE_USER("/api/users/{id}/deactivate"),

    // Endpoints relacionados con salas (rooms)
    GET_ALL_ROOMS("/api/rooms"),
    GET_ROOM_BY_ID("/api/rooms/{id}"),
    CREATE_ROOM("/api/rooms"),
    UPDATE_ROOM("/api/rooms/{id}"),
    DELETE_ROOM("/api/rooms/{id}"),
    SEARCH_ROOMS("/api/rooms/search"),
    ADD_USER_TO_ROOM("/api/rooms/{roomId}/users/{userId}"),
    REMOVE_USER_FROM_ROOM("/api/rooms/{roomId}/users/{userId}"),
    IS_ROOM_ACTIVE("/api/rooms/{id}/isActive"),

    // Endpoints relacionados con amigos (friendships)
    GET_ALL_FRIENDSHIPS("/api/friendships"),
    GET_FRIENDSHIP_BY_ID("/api/friendships/{id}"),
    CREATE_FRIENDSHIP("/api/friendships"),
    DELETE_FRIENDSHIP("/api/friendships/{id}"),

    // Endpoints relacionados con grupos (groups)
    GET_ALL_GROUPS("/api/groups"),
    GET_GROUP_BY_ID("/api/groups/{id}"),
    CREATE_GROUP("/api/groups"),
    DELETE_GROUP("/api/groups/{id}"),

    // Endpoints relacionados con medios (media)
    GET_ALL_MEDIA("/api/media"),
    GET_MEDIA_BY_ID("/api/media/{id}"),
    CREATE_MEDIA("/api/media"),
    DELETE_MEDIA("/api/media/{id}"),

    // Endpoints relacionados con perfiles (profiles)
    GET_ALL_PROFILES("/api/profiles"),
    GET_PROFILE_BY_ID("/api/profiles/{id}"),
    CREATE_PROFILE("/api/profiles"),
    UPDATE_PROFILE("/api/profiles/{id}"),
    DELETE_PROFILE("/api/profiles/{id}"),

    // Endpoints relacionados con mensajes (messages)
    GET_ALL_MESSAGES("/api/messages"),
    GET_MESSAGE_BY_ID("/api/messages/{id}"),
    CREATE_MESSAGE("/api/messages"),
    DELETE_MESSAGE("/api/messages/{id}"),

    // Endpoints relacionados con notificaciones (notifications)
    GET_ALL_NOTIFICATIONS("/api/notifications"),
    GET_NOTIFICATION_BY_ID("/api/notifications/{id}"),
    CREATE_NOTIFICATION("/api/notifications"),
    DELETE_NOTIFICATION("/api/notifications/{id}");

    private final String endpoint;

    JwtEndpoinds(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
