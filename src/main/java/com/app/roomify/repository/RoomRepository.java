package com.app.roomify.repository;

import com.app.roomify.repository.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    // Busca salas por nombre (búsqueda parcial)
    @Query("SELECT r FROM Room r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Room> findByNameContainingIgnoreCase(@Param("name") String name);

    // Buscar salas en las que un usuario está
    @Query("SELECT r FROM Room r JOIN r.users u WHERE u.id = :userId")
    List<Room> findByUsersId(@Param("userId") Integer userId);
}
