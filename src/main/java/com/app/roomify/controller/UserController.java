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
    @GetMapping("/users")
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
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    /**
     * Crear un nuevo usuario o actualizar uno existente.
     * @param userResponse El usuario a guardar o actualizar.
     * @return El usuario guardado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserResponse userResponse) {
        return userService.saveUser(userResponse);
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
        return userService.saveUser(userResponse);
    }

    /**
     * Eliminar un usuario por su ID.
     * @param id El ID del usuario a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    /**
     * Buscar usuarios por nombre (parcial).
     * @param name El nombre a buscar.
     * @return Lista de usuarios que coinciden.
     */
    @GetMapping("/search")
    public List<UserResponse> searchUsersByName(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    /**
     * Seguir a un usuario.
     * @param followerId El ID del usuario que sigue.
     * @param followeeId El ID del usuario a seguir.
     */
    @PostMapping("/{followerId}/follow/{followeeId}")
    public void followUser(@PathVariable int followerId, @PathVariable int followeeId) {
        userService.followUser(followerId, followeeId);
    }

    /**
     * Dejar de seguir a un usuario.
     * @param followerId El ID del usuario que deja de seguir.
     * @param followeeId El ID del usuario que es dejado de seguir.
     */
    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    public void unfollowUser(@PathVariable int followerId, @PathVariable int followeeId) {
        userService.unfollowUser(followerId, followeeId);
    }

    /**
     * Obtener los seguidores de un usuario.
     * @param id El ID del usuario.
     * @return Lista de seguidores del usuario.
     */
    @GetMapping("/{id}/followers")
    public List<UserResponse> getFollowers(@PathVariable int id) {
        return userService.getFollowers(id);
    }

    /**
     * Obtener las personas a las que un usuario sigue.
     * @param id El ID del usuario.
     * @return Lista de usuarios seguidos.
     */
    @GetMapping("/{id}/following")
    public List<UserResponse> getFollowing(@PathVariable int id) {
        return userService.getFollowing(id);
    }

    /**
     * Activar un usuario.
     * @param id El ID del usuario a activar.
     */
    @PostMapping("/{id}/activate")
    public void activateUser(@PathVariable int id) {
        userService.activateUser(id);
    }

    /**
     * Desactivar un usuario.
     * @param id El ID del usuario a desactivar.
     */
    @PostMapping("/{id}/deactivate")
    public void deactivateUser(@PathVariable int id) {
        userService.deactivateUser(id);
    }
}
