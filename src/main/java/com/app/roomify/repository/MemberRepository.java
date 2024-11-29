package com.app.roomify.repository;

import com.app.roomify.repository.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    /**
     * Encuentra un usuario por su correo electrónico utilizando una consulta personalizada.
     * @param email Correo del usuario.
     * @return Usuario con el correo dado.
     */
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Member findByEmail(@Param("email") String email);

    /**
     * Busca usuarios cuyo nombre contenga un texto específico, ignorando mayúsculas/minúsculas.
     * @param name Parte del nombre a buscar.
     * @return Lista de usuarios que coinciden.
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Member> findByNameContainingIgnoreCase(@Param("name") String name);
}
