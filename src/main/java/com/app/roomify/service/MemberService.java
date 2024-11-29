package com.app.roomify.service;

import com.app.roomify.repository.domain.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    // CRUD básico
    List<Member> getAllMembers(); // Obtener todos los usuarios

    Member getMemberById(Integer id); // Obtener un usuario por su ID

    Member saveMember(Member member); // Guardar o actualizar un usuario

    void deleteMember(Integer id); // Eliminar un usuario

    // Métodos específicos
    Member getMemberByEmail(String email); // Buscar un usuario por su correo electrónico

    List<Member> searchMembersByName(String name); // Buscar usuarios por nombre o parte del nombre

    void followMember(Integer followerId, Integer followedId); // Seguir a otro usuario

    void unfollowMember(Integer followerId, Integer followedId); // Dejar de seguir a un usuario

    List<Member> getFollowers(Integer userId); // Obtener la lista de seguidores de un usuario

    List<Member> getFollowing(Integer userId); // Obtener la lista de personas que sigue un usuario

    boolean isMemberActive(Integer id); // Comprobar si un usuario está activo

    void deactivateMember(Integer id); // Desactivar una cuenta de usuario

    void activateMember(Integer id); // Activar una cuenta de usuario

    String signUp(Map<String,String> requestMap);

    String login(Map<String,String> requestMap);
}
