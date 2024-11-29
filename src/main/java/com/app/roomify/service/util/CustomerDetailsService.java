package com.app.roomify.service.util;

import com.app.roomify.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import com.app.roomify.repository.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {


    final private MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username){
       log.info("Dentro de loadUserByUsername {}", username);
        // Busca al usuario en la base de datos
       Member user = memberRepository.findByEmail(username);

        // Convierte el usuario de dominio a un UserDetails compatible con Spring Security
        User.UserBuilder userBuilder = User.withUsername(user.getEmail());
        userBuilder.password(user.getPassword()); // Usa contraseñas cifradas
        userBuilder.roles(user.getMemberStatus().toString()); // Asigna roles en función de su estado

        return userBuilder.build();
    }

    public Member getUserDetail(){
        return null;
    }
}
