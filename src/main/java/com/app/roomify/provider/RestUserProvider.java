package com.app.roomify.provider;

import com.app.roomify.domain.User;

import java.util.List;

public interface RestUserProvider {

    // CRUD básico
    List<User> getAllUsers(); // Obtener todos los usuarios

    User getUserById(Long id); // Obtener un usuario por su ID

    User saveUser(User user); // Guardar o actualizar un usuario

    void deleteUser(Long id); // Eliminar un usuario

    // Métodos específicos
    User getUserByEmail(String email); // Buscar un usuario por su correo electrónico

    List<User> searchUsersByName(String name); // Buscar usuarios por nombre o parte del nombre

    boolean followUser(Long followerId, Long followedId); // Seguir a otro usuario

    boolean unfollowUser(Long followerId, Long followedId); // Dejar de seguir a un usuario

    List<User> getFollowers(Long userId); // Obtener la lista de seguidores de un usuario

    List<User> getFollowing(Long userId); // Obtener la lista de personas que sigue un usuario

    boolean isUserActive(Long id); // Comprobar si un usuario está activo

    void deactivateUser(Long id); // Desactivar una cuenta de usuario

    void activateUser(Long id); // Activar una cuenta de usuario
}
