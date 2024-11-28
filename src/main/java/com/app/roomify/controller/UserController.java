package com.app.roomify.controller;

import com.app.roomify.domain.User;
import com.app.roomify.provider.exchange.response.UserResponse;
import com.app.roomify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    /**
     * Obtener todos los usuarios.
     * @return Lista de todos los usuarios.
     */
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        // Convertir cada User a UserResponse usando ConversionService
        List<UserResponse> userResponses = users.stream()
                .map(user -> conversionService.convert(user, UserResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    /**
     * Obtener un usuario por su ID.
     * @param id El ID del usuario a buscar.
     * @return El usuario con el ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        UserResponse userResponse = conversionService.convert(user, UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    /**
     * Crear un nuevo usuario o actualizar uno existente.
     * @param userResponse El usuario a guardar o actualizar.
     * @return El usuario guardado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserResponse userResponse) {
        // Convierte el UserResponse a User para el almacenamiento
        User user = conversionService.convert(userResponse, User.class);
        user = userService.saveUser(user);
        // Convierte el User guardado nuevamente a UserResponse
        return conversionService.convert(user, UserResponse.class);
    }

    /**
     * Actualizar un usuario existente.
     * @param id El ID del usuario a actualizar.
     * @param userResponse El usuario con los datos actualizados.
     * @return El usuario actualizado.
     */
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable int id, @RequestBody UserResponse userResponse) {
        userResponse.setId(id);

        // Convierte el UserResponse a User para la actualización
        User user = conversionService.convert(userResponse, User.class);
        user = userService.saveUser(user);

        // Convierte el User actualizado nuevamente a UserResponse
        return conversionService.convert(user, UserResponse.class);
    }

    /**
     * Eliminar un usuario por su ID.
     * @param id El ID del usuario a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar usuarios por nombre (parcial).
     * @param name El nombre a buscar.
     * @return Lista de usuarios que coinciden.
     */
    @GetMapping("/search")
    public List<UserResponse> searchUsersByName(@RequestParam String name) {
        List<User> users = userService.searchUsersByName(name);
        return users.stream()
                .map(user -> conversionService.convert(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Seguir a un usuario.
     * @param followerId El ID del usuario que sigue.
     * @param followeeId El ID del usuario a seguir.
     */
    @PostMapping("/{followerId}/follow/{followeeId}")
    public ResponseEntity<Void> followUser(@PathVariable int followerId, @PathVariable int followeeId) {
        userService.followUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Dejar de seguir a un usuario.
     * @param followerId El ID del usuario que deja de seguir.
     * @param followeeId El ID del usuario que es dejado de seguir.
     */
    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable int followerId, @PathVariable int followeeId) {
        userService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Obtener los seguidores de un usuario.
     * @param id El ID del usuario.
     * @return Lista de seguidores del usuario.
     */
    @GetMapping("/{id}/followers")
    public List<UserResponse> getFollowers(@PathVariable int id) {
        List<User> followers = userService.getFollowers(id);
        return followers.stream()
                .map(user -> conversionService.convert(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Obtener las personas a las que un usuario sigue.
     * @param id El ID del usuario.
     * @return Lista de usuarios seguidos.
     */
    @GetMapping("/{id}/following")
    public List<UserResponse> getFollowing(@PathVariable int id) {
        List<User> following = userService.getFollowing(id);
        return following.stream()
                .map(user -> conversionService.convert(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Activar un usuario.
     * @param id El ID del usuario a activar.
     */
    @PostMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable int id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Desactivar un usuario.
     * @param id El ID del usuario a desactivar.
     */
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable int id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
}
