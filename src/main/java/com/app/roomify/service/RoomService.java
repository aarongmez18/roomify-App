package com.app.roomify.service;
import com.app.roomify.domain.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    /**
     * Obtener todas las salas.
     * @return Lista de todas las salas.
     */
    List<Room> getAllRooms();

    /**
     * Obtener una sala por su ID.
     * @param id El ID de la sala.
     * @return La sala encontrada.
     */
    Room getRoomById(Integer id);

    /**
     * Crear una nueva sala.
     * @param room La sala a crear.
     * @return La sala creada.
     */
    Room createRoom(Room room);

    /**
     * Actualizar una sala existente.
     * @param id El ID de la sala a actualizar.
     * @param room La sala con los datos actualizados.
     * @return La sala actualizada.
     */
    Room updateRoom(Integer id, Room room);

    /**
     * Eliminar una sala por su ID.
     * @param id El ID de la sala a eliminar.
     */
    void deleteRoom(Integer id);

    /**
     * Buscar salas por nombre (búsqueda parcial).
     * @param name El término a buscar.
     * @return Lista de salas que coinciden.
     */
    List<Room> searchRoomsByName(String name);

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

