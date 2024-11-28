package com.app.roomify.service.impl;

import com.app.roomify.domain.Room;
import com.app.roomify.domain.User;
import com.app.roomify.provider.exchange.repository.RoomRepository;
import com.app.roomify.provider.exchange.repository.UserRepository;
import com.app.roomify.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    // Obtener todas las salas
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Obtener una sala por su ID
    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + id));
    }

    // Crear una nueva sala
    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Actualizar una sala existente
    @Override
    public Room updateRoom(Integer id, Room room) {
        Room existingRoom = getRoomById(id);
        existingRoom.setName(room.getName());
        existingRoom.setDescription(room.getDescription());
        existingRoom.setLatitude(room.getLatitude());
        existingRoom.setLongitude(room.getLongitude());
        existingRoom.setMaxCapacity(room.getMaxCapacity());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setPublic(room.isPublic());
        existingRoom.setExpirationDate(room.getExpirationDate());
        existingRoom.setProfilePhoto(room.getProfilePhoto());
        return roomRepository.save(existingRoom);
    }

    // Eliminar una sala por su ID
    @Override
    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    // Buscar salas por nombre (búsqueda parcial)
    @Override
    public List<Room> searchRoomsByName(String name) {
        return roomRepository.findByNameContainingIgnoreCase(name);
    }

    // Verificar si una sala está activa (según la fecha de expiración)
    @Override
    public boolean isRoomActive(Integer id) {
        Room room = getRoomById(id);
        return room.getExpirationDate().after(new java.util.Date());
    }
    // Agregar un usuario a una sala
    @Override
    public boolean addUserToRoom(Integer roomId, Integer userId) {
        // Obtener la sala y el usuario
        Room roomOpt = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
        User userOpt = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

            // Verificar si la sala no ha alcanzado el número máximo de usuarios
            if (roomOpt.getUsers().size() < roomOpt.getMaxCapacity()) {
                // Añadir el usuario a la sala
                roomOpt.getUsers().add(userOpt);
                // Añadir la sala al usuario (relación bidireccional)
                userOpt.getRooms().add(roomOpt);

                // Guardar los cambios en la base de datos
                roomRepository.save(roomOpt);
                userRepository.save(userOpt);

                return true; // Usuario añadido con éxito
            } else {
                // La sala ya está llena
                return false;
            }
    }




    // Eliminar un usuario de una sala
    @Override
    public boolean removeUserFromRoom(Integer roomId, Integer userId) {
        // Obtener la sala y el usuario
        Room roomOpt = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
        User userOpt = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

            // Verificar si el usuario está en la sala
            if (roomOpt.getUsers().contains(userOpt)) {
                // Eliminar al usuario de la sala
                roomOpt.getUsers().remove(userOpt);
                // Eliminar la sala del usuario
                userOpt.getRooms().remove(roomOpt);

                // Guardar los cambios en la base de datos
                roomRepository.save(roomOpt);
                userRepository.save(userOpt);

                return true; // Usuario eliminado con éxito
            } else {
                // El usuario no está en la sala
                return false;
            }
    }
}


