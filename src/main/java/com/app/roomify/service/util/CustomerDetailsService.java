package com.app.roomify.service.util;

import com.app.roomify.provider.exchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {


    final private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username){
       log.info("Dentro de loadUserByUsername {}", username);
        // Busca al usuario en la base de datos
       com.app.roomify.domain.User user = userRepository.findByEmail(username);

        // Convierte el usuario de dominio a un UserDetails compatible con Spring Security
        User.UserBuilder userBuilder = User.withUsername(user.getEmail());
        userBuilder.password(user.getPassword()); // Usa contraseñas cifradas
        userBuilder.roles(user.getUserStatus().toString()); // Asigna roles en función de su estado

        return userBuilder.build();
    }

    public com.app.roomify.domain.User getUserDetail(){
        return null;
    }
}
