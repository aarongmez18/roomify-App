package com.app.roomify.provider.exchange.repository;

import com.app.roomify.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * Encuentra un usuario por su correo electrónico utilizando una consulta personalizada.
     * @param email Correo del usuario.
     * @return Usuario con el correo dado.
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    /**
     * Busca usuarios cuyo nombre contenga un texto específico, ignorando mayúsculas/minúsculas.
     * @param name Parte del nombre a buscar.
     * @return Lista de usuarios que coinciden.
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameContainingIgnoreCase(@Param("name") String name);
}
