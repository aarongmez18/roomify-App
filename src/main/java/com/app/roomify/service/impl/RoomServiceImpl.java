package com.app.roomify.service.impl;

import com.app.roomify.exception.AppErrors;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.repository.domain.Member;
import com.app.roomify.repository.domain.Room;
import com.app.roomify.repository.RoomRepository;
import com.app.roomify.repository.MemberRepository;
import com.app.roomify.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    // Obtener todas las salas
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Obtener una sala por su ID
    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomifyException(AppErrors.ERROR_ROOM_NOT_FOUND));
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
        Room roomOpt = roomRepository.findById(roomId).orElseThrow(() -> new RoomifyException(AppErrors.ERROR_ROOM_NOT_FOUND));
        Member memberOpt = memberRepository.findById(userId).orElseThrow(() -> new RoomifyException(AppErrors.ERROR_USER_NOT_FOUND));

            // Verificar si la sala no ha alcanzado el número máximo de usuarios
            if (roomOpt.getMembers().size() < roomOpt.getMaxCapacity()) {
                // Añadir el usuario a la sala
                roomOpt.getMembers().add(memberOpt);
                // Añadir la sala al usuario (relación bidireccional)
                memberOpt.getRooms().add(roomOpt);

                // Guardar los cambios en la base de datos
                roomRepository.save(roomOpt);
                memberRepository.save(memberOpt);

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
        Room roomOpt = roomRepository.findById(roomId).orElseThrow(() -> new RoomifyException(AppErrors.ERROR_ROOM_NOT_FOUND));
        Member memberOpt = memberRepository.findById(userId).orElseThrow(() -> new RoomifyException(AppErrors.ERROR_USER_NOT_FOUND));

            // Verificar si el usuario está en la sala
            if (roomOpt.getMembers().contains(memberOpt)) {
                // Eliminar al usuario de la sala
                roomOpt.getMembers().remove(memberOpt);
                // Eliminar la sala del usuario
                memberOpt.getRooms().remove(roomOpt);

                // Guardar los cambios en la base de datos
                roomRepository.save(roomOpt);
                memberRepository.save(memberOpt);

                return true; // Usuario eliminado con éxito
            } else {
                // El usuario no está en la sala
                return false;
            }
    }
}


