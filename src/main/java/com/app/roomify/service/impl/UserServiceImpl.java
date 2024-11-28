package com.app.roomify.service.impl;

import com.app.roomify.domain.User;
import com.app.roomify.domain.enums.UserStatus;
import com.app.roomify.provider.exchange.repository.UserRepository;
import com.app.roomify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public static final String ERROR_USER_NOT_FOUND = "User not found with the given ID";

    // Obtener todos los usuarios
    @Override
    public List<User> getAllUsers() {
        log.info("INIT - UserService -> getAllUsers()");
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    @Override
    public User getUserById(Integer id) {
        log.info("INIT - UserService -> findById(id)");
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_USER_NOT_FOUND));
    }

    // Guardar o actualizar un usuario
    @Override
    public User saveUser(User user) {
        log.info("INIT - UserService -> save(user)");
        return userRepository.save(user);
    }

    // Eliminar un usuario
    @Override
    public void deleteUser(Integer id) {
        log.info("INIT - UserService -> deleteById(id)");
        userRepository.deleteById(id);
    }

    // Buscar un usuario por email
    @Override
    public User getUserByEmail(String email) {
        log.info("INIT - UserService -> findByEmail(email)");
        return userRepository.findByEmail(email);
    }

    // Buscar usuarios por nombre (Implementado)
    @Override
    public List<User> searchUsersByName(String name) {
        log.info("INIT - UserService -> findByNameContainingIgnoreCase(name)");
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    // Seguir a un usuario
    @Override
    public void followUser(Integer followerId, Integer followeeId) {
        log.info("INIT - UserService -> followUser(followerId,followeeId)");
        User follower = userRepository.findById(followerId) .orElseThrow(() -> new RuntimeException(ERROR_USER_NOT_FOUND + followerId));
        User followee = userRepository.findById(followeeId) .orElseThrow(() -> new RuntimeException(ERROR_USER_NOT_FOUND + followeeId));


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
        log.info("INIT - UserService -> unfollowUser(followerId,followeeId)");
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
        log.info("INIT - UserService -> getFollowers(userId)");
        return userRepository.findById(userId)
                .map(User::getFollowers)
                .map(List::copyOf)
                .orElse(List.of());
    }

    // Obtener los usuarios que sigue un usuario
    @Override
    public List<User> getFollowing(Integer userId) {
        log.info("INIT - UserService -> getFollowing(userId)");
        return userRepository.findById(userId)
                .map(User::getFollowing)
                .map(List::copyOf)
                .orElse(List.of());
    }

    // Verificar si un usuario está activo
    @Override
    public boolean isUserActive(Integer id) {
        log.info("INIT - UserService -> isUserActive(id)");
        return userRepository.findById(id)
                .map(user -> user.getUserStatus() == UserStatus.ACTIVE)
                .orElse(false);
    }

    // Desactivar un usuario
    @Override
    public void deactivateUser(Integer id) {
        log.info("INIT - UserService -> deactivateUser(id)");
        userRepository.findById(id).ifPresent(user -> {
            user.setUserStatus(UserStatus.INACTIVE);
            userRepository.save(user);
        });
    }

    // Activar un usuario
    @Override
    public void activateUser(Integer id) {
        log.info("INIT - UserService -> activateUser(id)");
        userRepository.findById(id).ifPresent(user -> {
            user.setUserStatus(UserStatus.ACTIVE);
            userRepository.save(user);
        });
    }
}
