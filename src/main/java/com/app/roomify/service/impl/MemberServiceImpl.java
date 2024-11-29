package com.app.roomify.service.impl;

import com.app.roomify.exception.AppErrors;
import com.app.roomify.exception.RoomifyException;
import com.app.roomify.repository.domain.Member;
import com.app.roomify.repository.domain.enums.MemberStatus;
import com.app.roomify.repository.MemberRepository;
import com.app.roomify.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // Obtener todos los usuarios
    @Override
    public List<Member> getAllMembers() {
        log.info("INIT - UserService -> getAllUsers()");
        return memberRepository.findAll();
    }

    // Obtener un usuario por ID
    @Override
    public Member getMemberById(Integer id) {
        log.info("INIT - MemberService -> findById(id)");
        return memberRepository.findById(id).orElseThrow(() -> new RoomifyException(AppErrors.ERROR_USER_NOT_FOUND));
    }

    // Guardar o actualizar un usuario
    @Override
    public Member saveMember(Member member) {
        log.info("INIT - MemberService -> save(member)");
        return memberRepository.save(member);
    }

    // Eliminar un usuario
    @Override
    public void deleteMember(Integer id) {
        log.info("INIT - MemberService -> deleteById(id)");
        memberRepository.deleteById(id);
    }

    // Buscar un usuario por email
    @Override
    public Member getMemberByEmail(String email) {
        log.info("INIT - MemberService -> findByEmail(email)");
        return memberRepository.findByEmail(email);
    }

    // Buscar usuarios por nombre (Implementado)
    @Override
    public List<Member> searchMembersByName(String name) {
        log.info("INIT - MemberService -> findByNameContainingIgnoreCase(name)");
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    // Seguir a un usuario
    @Override
    public void followMember(Integer followerId, Integer followeeId) {
        log.info("INIT - MemberService -> followMember(followerId,followeeId)");
        Member follower = memberRepository.findById(followerId) .orElseThrow(() -> new RoomifyException(AppErrors.ERROR_USER_NOT_FOUND));
        Member followee = memberRepository.findById(followeeId) .orElseThrow(() -> new RoomifyException(AppErrors.ERROR_USER_NOT_FOUND));


            // Agregar la relación
        follower.getFollowing().add(followee);
        followee.getFollowers().add(follower);

            // Guardar cambios
            memberRepository.save(follower);
            memberRepository.save(followee);
    }

    // Dejar de seguir a un usuario
    @Override
    public void unfollowMember(Integer followerId, Integer followeeId) {
        log.info("INIT - MemberService -> unfollowUser(followerId,followeeId)");
        Member followerMember = memberRepository.findById(followerId) .orElseThrow(() -> new RuntimeException("User not found with ID: " + followerId));
        Member followeeMember = memberRepository.findById(followeeId) .orElseThrow(() -> new RuntimeException("User not found with ID: " + followerId));

            // Eliminar la relación
            followerMember.getFollowing().remove(followeeMember);
            followeeMember.getFollowers().remove(followerMember);

            // Guardar cambios
            memberRepository.save(followerMember);
            memberRepository.save(followeeMember);

    }

    // Obtener los seguidores de un usuario
    @Override
    public List<Member> getFollowers(Integer userId) {
        log.info("INIT - MemberService -> getFollowers(userId)");
        return memberRepository.findById(userId)
                .map(Member::getFollowers)
                .map(List::copyOf)
                .orElse(List.of());
    }

    // Obtener los usuarios que sigue un usuario
    @Override
    public List<Member> getFollowing(Integer userId) {
        log.info("INIT - MemberService -> getFollowing(userId)");
        return memberRepository.findById(userId)
                .map(Member::getFollowing)
                .map(List::copyOf)
                .orElse(List.of());
    }

    // Verificar si un usuario está activo
    @Override
    public boolean isMemberActive(Integer id) {
        log.info("INIT - MemberService -> isUserActive(id)");
        return memberRepository.findById(id)
                .map(user -> user.getMemberStatus() == MemberStatus.ACTIVE)
                .orElse(false);
    }

    // Desactivar un usuario
    @Override
    public void deactivateMember(Integer id) {
        log.info("INIT - MemberService -> deactivateMember(id)");
        memberRepository.findById(id).ifPresent(user -> {
            user.setMemberStatus(MemberStatus.INACTIVE);
            memberRepository.save(user);
        });
    }

    // Activar un usuario
    @Override
    public void activateMember(Integer id) {
        log.info("INIT - MemberService -> activateMember(id)");
        memberRepository.findById(id).ifPresent(user -> {
            user.setMemberStatus(MemberStatus.ACTIVE);
            memberRepository.save(user);
        });
    }

    @Override
    public String signUp(Map<String, String> requestMap) {
        return "";
    }

    @Override
    public String login(Map<String, String> requestMap) {
        return "";
    }
}
