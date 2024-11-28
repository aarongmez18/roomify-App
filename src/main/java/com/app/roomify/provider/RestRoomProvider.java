package com.app.roomify.provider;

import com.app.roomify.domain.Room;
import com.app.roomify.provider.exchange.response.RoomResponse;
import java.util.List;

public interface RestRoomProvider {

    /**
     * Obtener todas las salas.
     *
     * @return Lista de todas las salas como RoomResponse.
     */
    List<Room> getAllRooms();

    /**
     * Obtener una sala por su ID.
     * @param id El ID de la sala.
     * @return La sala encontrada como RoomResponse.
     */
    RoomResponse getRoomById(Integer id);

    /**
     * Crear una nueva sala.
     * @param roomResponse Los datos de la nueva sala.
     * @return La sala creada como RoomResponse.
     */
    RoomResponse createRoom(RoomResponse roomResponse);

    /**
     * Actualizar una sala existente.
     * @param id El ID de la sala a actualizar.
     * @param roomResponse Los nuevos datos de la sala.
     * @return La sala actualizada como RoomResponse.
     */
    RoomResponse updateRoom(Integer id, RoomResponse roomResponse);

    /**
     * Eliminar una sala por su ID.
     * @param id El ID de la sala a eliminar.
     */
    void deleteRoom(Integer id);

    /**
     * Buscar salas por nombre (búsqueda parcial).
     * @param name El término a buscar.
     * @return Lista de salas que coinciden como RoomResponse.
     */
    List<RoomResponse> searchRoomsByName(String name);

    /**
     * Verificar si una sala está activa (según la fecha de expiración).
     * @param id El ID de la sala.
     * @return true si está activa, false en caso contrario.
     */
    boolean isRoomActive(Integer id);

    /**
     * Añadir un usuario a una sala.
     * @param roomId El ID de la sala.
     * @param userId El ID del usuario.
     * @return true si se añadió correctamente, false en caso contrario.
     */
    boolean addUserToRoom(Integer roomId, Integer userId);

    /**
     * Eliminar un usuario de una sala.
     * @param roomId El ID de la sala.
     * @param userId El ID del usuario.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    boolean removeUserFromRoom(Integer roomId, Integer userId);
}

