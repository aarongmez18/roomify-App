package com.app.roomify.controller;

import com.app.roomify.domain.Room;
import com.app.roomify.provider.exchange.response.RoomResponse;
import com.app.roomify.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<Room> rooms = roomService.getAllRooms();

        // Convertir cada Room a RoomResponse usando ConversionService
        List<RoomResponse> roomResponses = rooms.stream()
                .map(room -> conversionService.convert(room, RoomResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(roomResponses);
    }

    /**
     * Obtener una sala por su ID.
     * @param id El ID de la sala a buscar.
     * @return La sala con el ID especificado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable int id) {
        Room room = roomService.getRoomById(id);
        RoomResponse roomResponse = conversionService.convert(room, RoomResponse.class);
        return ResponseEntity.ok(roomResponse);
    }

    /**
     * Crear una nueva sala.
     * @param roomResponse La sala a guardar.
     * @return La sala creada.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse createRoom(@RequestBody RoomResponse roomResponse) {
        // Convierte el RoomResponse a Room para el almacenamiento
        Room room = conversionService.convert(roomResponse, Room.class);
        room = roomService.createRoom(room);
        // Convierte el Room guardado nuevamente a RoomResponse
        return conversionService.convert(room, RoomResponse.class);
    }

    /**
     * Actualizar una sala existente.
     * @param id El ID de la sala a actualizar.
     * @param roomResponse La sala con los datos actualizados.
     * @return La sala actualizada.
     */
    @PutMapping("/{id}")
    public RoomResponse updateRoom(@PathVariable int id, @RequestBody RoomResponse roomResponse) {
        roomResponse.setId(id);

        // Convierte el RoomResponse a Room para la actualización
        Room room = conversionService.convert(roomResponse, Room.class);
        room = roomService.updateRoom(id, room);

        // Convierte el Room actualizado nuevamente a RoomResponse
        return conversionService.convert(room, RoomResponse.class);
    }

    /**
     * Eliminar una sala por su ID.
     * @param id El ID de la sala a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar salas por nombre.
     * @param name El nombre a buscar.
     * @return Lista de salas que coinciden.
     */
    @GetMapping("/search")
    public List<RoomResponse> searchRoomsByName(@RequestParam String name) {
        List<Room> rooms = roomService.searchRoomsByName(name);
        return rooms.stream()
                .map(room -> conversionService.convert(room, RoomResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * Añadir un usuario a una sala.
     * @param roomId El ID de la sala.
     * @param userId El ID del usuario.
     */
    @PostMapping("/{roomId}/users/{userId}")
    public ResponseEntity<Void> addUserToRoom(@PathVariable int roomId, @PathVariable int userId) {
        roomService.addUserToRoom(roomId, userId);
        return ResponseEntity.ok().build();
    }

    /**
     * Eliminar un usuario de una sala.
     * @param roomId El ID de la sala.
     * @param userId El ID del usuario.
     */
    @DeleteMapping("/{roomId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromRoom(@PathVariable int roomId, @PathVariable int userId) {
        roomService.removeUserFromRoom(roomId, userId);
        return ResponseEntity.ok().build();
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
