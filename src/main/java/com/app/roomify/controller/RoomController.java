package com.app.roomify.controller;

import com.app.roomify.repository.domain.Room;
import com.app.roomify.controller.response.RoomResponse;
import com.app.roomify.service.RoomService;
import com.app.roomify.exception.RoomifyException;
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
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final ConversionService conversionService;

    /**
     * Obtener todas las salas.
     * @return Lista de todas las salas.
     */
    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            List<RoomResponse> roomResponses = rooms.stream()
                    .map(room -> conversionService.convert(room, RoomResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(roomResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Obtener una sala por su ID.
     * @param id El ID de la sala a buscar.
     * @return La sala con el ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id) {
        try {
            Room room = roomService.getRoomById(id);
            RoomResponse roomResponse = conversionService.convert(room, RoomResponse.class);
            return ResponseEntity.ok(roomResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Crear una nueva sala.
     * @param room La sala a guardar.
     * @return La sala creada.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody Room room) {
        try {
            RoomResponse roomResponse = conversionService.convert(room, RoomResponse.class);
            roomService.createRoom(room);
            return ResponseEntity.ok(roomResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Actualizar una sala existente.
     * @param id El ID de la sala a actualizar.
     * @param roomResponse La sala con los datos actualizados.
     * @return La sala actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable int id, @Valid @RequestBody RoomResponse roomResponse) {
        try {
            roomResponse.setId(id);
            Room room = conversionService.convert(roomResponse, Room.class);
            room = roomService.updateRoom(id, room);
            RoomResponse updatedRoomResponse = conversionService.convert(room, RoomResponse.class);
            return ResponseEntity.ok(updatedRoomResponse);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Eliminar una sala por su ID.
     * @param id El ID de la sala a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.noContent().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Buscar salas por nombre.
     * @param name El nombre a buscar.
     * @return Lista de salas que coinciden.
     */
    @GetMapping("/search")
    public ResponseEntity<List<RoomResponse>> searchRoomsByName(@RequestParam String name) {
        try {
            List<Room> rooms = roomService.searchRoomsByName(name);
            List<RoomResponse> roomResponses = rooms.stream()
                    .map(room -> conversionService.convert(room, RoomResponse.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(roomResponses);
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Añadir un usuario a una sala.
     * @param roomId El ID de la sala.
     * @param userId El ID del usuario.
     */
    @PostMapping("/{roomId}/users/{userId}")
    public ResponseEntity<Void> addUserToRoom(@PathVariable int roomId, @PathVariable int userId) {
        try {
            roomService.addUserToRoom(roomId, userId);
            return ResponseEntity.ok().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Eliminar un usuario de una sala.
     * @param roomId El ID de la sala.
     * @param userId El ID del usuario.
     */
    @DeleteMapping("/{roomId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromRoom(@PathVariable int roomId, @PathVariable int userId) {
        try {
            roomService.removeUserFromRoom(roomId, userId);
            return ResponseEntity.ok().build();
        } catch (RoomifyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Comprobar si una sala está activa.
     * @param id El ID de la sala.
     * @return True si la sala está activa.
     */
    @GetMapping("/{id}/isActive")
    public ResponseEntity<Boolean> isRoomActive(@PathVariable int id) {
        boolean isActive = roomService.isRoomActive(id);
        return ResponseEntity.ok(isActive);
    }
}
