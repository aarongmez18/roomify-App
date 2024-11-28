package com.app.roomify.service;

import com.app.roomify.domain.User;
import java.util.List;

public interface UserService {

    // CRUD básico
    List<User> getAllUsers(); // Obtener todos los usuarios

    User getUserById(Integer id); // Obtener un usuario por su ID

    User saveUser(User user); // Guardar o actualizar un usuario

    void deleteUser(Integer id); // Eliminar un usuario

    // Métodos específicos
    User getUserByEmail(String email); // Buscar un usuario por su correo electrónico

    List<User> searchUsersByName(String name); // Buscar usuarios por nombre o parte del nombre

    void followUser(Integer followerId, Integer followedId); // Seguir a otro usuario

    void unfollowUser(Integer followerId, Integer followedId); // Dejar de seguir a un usuario

    List<User> getFollowers(Integer userId); // Obtener la lista de seguidores de un usuario

    List<User> getFollowing(Integer userId); // Obtener la lista de personas que sigue un usuario

    boolean isUserActive(Integer id); // Comprobar si un usuario está activo

    void deactivateUser(Integer id); // Desactivar una cuenta de usuario

    void activateUser(Integer id); // Activar una cuenta de usuario
}
