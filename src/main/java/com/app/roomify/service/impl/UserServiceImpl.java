package com.app.roomify.service.impl;

import com.app.roomify.domain.User;
import com.app.roomify.provider.exchange.repository.UserRepository;
import com.app.roomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Obtener todos los usuarios
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Guardar o actualizar un usuario
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Eliminar un usuario
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Buscar un usuario por email
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Buscar usuarios por nombre (Implementado)
    @Override
    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    // Seguir a un usuario
    @Override
    public void followUser(Integer followerId, Integer followeeId) {
        User follower = userRepository.findById(followerId) .orElseThrow(() -> new RuntimeException("User not found with ID: " + followerId));
        User followee = userRepository.findById(followeeId) .orElseThrow(() -> new RuntimeException("User not found with ID: " + followeeId));


            // Agregar la relación
        follower.getFollowing().add(followee);
        followee.getFollowers().add(follower);

            // Guardar cambios
            userRepository.save(follower);
            userRepository.save(followee);
    }

    // Dejar de seguir a un usuario
    @Override
    public void unfollowUser(Integer followerId, Integer followeeId) {
        User followerUser = userRepository.findById(followerId) .orElseThrow(() -> new RuntimeException("User not found with ID: " + followerId));
        User followeeUser = userRepository.findById(followeeId) .orElseThrow(() -> new RuntimeException("User not found with ID: " + followerId));

            // Eliminar la relación
            followerUser.getFollowing().remove(followeeUser);
            followeeUser.getFollowers().remove(followerUser);

            // Guardar cambios
            userRepository.save(followerUser);
            userRepository.save(followeeUser);

    }

    // Obtener los seguidores de un usuario
    @Override
    public List<User> getFollowers(Integer userId) {
        return userRepository.findById(userId)
                .map(User::getFollowers)
                .map(List::copyOf)
                .orElse(List.of());
    }

    // Obtener los usuarios que sigue un usuario
    @Override
    public List<User> getFollowing(Integer userId) {
        return userRepository.findById(userId)
                .map(User::getFollowing)
                .map(List::copyOf)
                .orElse(List.of());
    }

    // Verificar si un usuario está activo
    @Override
    public boolean isUserActive(Integer id) {
        return userRepository.findById(id)
                .map(user -> user.getStatus() == User.UserStatus.ACTIVE)
                .orElse(false);
    }

    // Desactivar un usuario
    @Override
    public void deactivateUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setStatus(User.UserStatus.INACTIVE);
            userRepository.save(user);
        });
    }

    // Activar un usuario
    @Override
    public void activateUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setStatus(User.UserStatus.ACTIVE);
            userRepository.save(user);
        });
    }
}
