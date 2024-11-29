package com.app.roomify.controller;

import com.app.roomify.repository.domain.Profile;
import com.app.roomify.controller.response.ProfileResponse;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ConversionService conversionService;

    /**
     * Obtener todos los perfiles.
     *
     * @return Lista de todos los perfiles.
     */
    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        try {
            List<Profile> profiles = profileService.getAllProfiles();
            List<ProfileResponse> profileResponses = profiles.stream()
                    .map(profile -> conversionService.convert(profile, ProfileResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(profileResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener un perfil por su ID.
     *
     * @param id ID del perfil.
     * @return Detalles del perfil.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable int id) {
        try {
            Profile profile = profileService.getProfileById(id);
            ProfileResponse profileResponse = conversionService.convert(profile, ProfileResponse.class);
            return ResponseEntity.ok(profileResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Crear un nuevo perfil.
     *
     * @param profileResponse Detalles del perfil a crear.
     * @return Detalles del perfil creado.
     */
    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@Valid @RequestBody ProfileResponse profileResponse) {
        try {
            Profile profile = conversionService.convert(profileResponse, Profile.class);
            profile = profileService.saveProfile(profile);
            ProfileResponse response = conversionService.convert(profile, ProfileResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Actualizar un perfil existente.
     *
     * @param id ID del perfil a actualizar.
     * @param profileResponse Datos actualizados del perfil.
     * @return El perfil actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable int id, @Valid @RequestBody ProfileResponse profileResponse) {
        try {
            profileResponse.setId(id);
            Profile profile = conversionService.convert(profileResponse, Profile.class);
            profile = profileService.saveProfile(profile);
            ProfileResponse response = conversionService.convert(profile, ProfileResponse.class);
            return ResponseEntity.ok(response);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Eliminar un perfil por su ID.
     *
     * @param id ID del perfil a eliminar.
     * @return Respuesta vacía en caso de éxito.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        try {
            profileService.deleteProfile(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
