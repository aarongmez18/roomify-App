package com.app.roomify.config;

import com.app.roomify.config.properties.JwtEndpoinds;
import com.app.roomify.service.util.CustomerDetailsService;
import com.app.roomify.service.util.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomerDetailsService customerDetailsService;
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        configureCsrf(http);
        configureAuthorization(http);
        configureUserDetailsService(http);
        configureSessionManagement(http);
        configureFilters(http);
        configureExceptionHandling(http);
        return http.build();
    }

    private void configureCsrf(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
    }

    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                registry -> registry
                        // Permitir acceso sin autenticación a los endpoints de Swagger y documentación
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()

                        // Permitir acceso sin autenticación a todos los demás endpoints públicos
                        .requestMatchers(
                                JwtEndpoinds.GET_ALL_USERS.getEndpoint(),
                                JwtEndpoinds.GET_USER_BY_ID.getEndpoint(),
                                JwtEndpoinds.GET_ALL_ROOMS.getEndpoint(),
                                JwtEndpoinds.GET_ROOM_BY_ID.getEndpoint(),
                                JwtEndpoinds.GET_ALL_GROUPS.getEndpoint(),
                                JwtEndpoinds.GET_GROUP_BY_ID.getEndpoint(),
                                JwtEndpoinds.GET_ALL_MESSAGES.getEndpoint(),
                                JwtEndpoinds.GET_MESSAGE_BY_ID.getEndpoint(),
                                JwtEndpoinds.GET_ALL_NOTIFICATIONS.getEndpoint(),
                                JwtEndpoinds.GET_NOTIFICATION_BY_ID.getEndpoint(),

                                // Endpoints relacionados con medios (media)
                                JwtEndpoinds.GET_ALL_MEDIA.getEndpoint(),
                                JwtEndpoinds.GET_MEDIA_BY_ID.getEndpoint(),
                                JwtEndpoinds.CREATE_MEDIA.getEndpoint(),
                                JwtEndpoinds.DELETE_MEDIA.getEndpoint(),

                                // Endpoints relacionados con perfiles (profiles)
                                JwtEndpoinds.GET_ALL_PROFILES.getEndpoint(),
                                JwtEndpoinds.GET_PROFILE_BY_ID.getEndpoint(),

                                // Endpoints relacionados con amigos (friendships)
                                JwtEndpoinds.GET_ALL_FRIENDSHIPS.getEndpoint(),
                                JwtEndpoinds.GET_FRIENDSHIP_BY_ID.getEndpoint(),

                                // Endpoints de búsqueda de salas
                                JwtEndpoinds.SEARCH_ROOMS.getEndpoint(),

                                // Endpoints de activación y desactivación de usuarios
                                JwtEndpoinds.ACTIVATE_USER.getEndpoint(),
                                JwtEndpoinds.DEACTIVATE_USER.getEndpoint(),

                                // Endpoints de búsqueda de usuarios
                                JwtEndpoinds.SEARCH_USERS.getEndpoint()
                        )
                        .permitAll()

                        // Solo permitir acceso al ADMIN para ciertos endpoints, como gestión de usuarios, salas y perfiles
                        .requestMatchers(
                                JwtEndpoinds.CREATE_USER.getEndpoint(),
                                JwtEndpoinds.UPDATE_USER.getEndpoint(),
                                JwtEndpoinds.DELETE_USER.getEndpoint(),
                                JwtEndpoinds.CREATE_ROOM.getEndpoint(),
                                JwtEndpoinds.UPDATE_ROOM.getEndpoint(),
                                JwtEndpoinds.DELETE_ROOM.getEndpoint(),
                                JwtEndpoinds.CREATE_GROUP.getEndpoint(),
                                JwtEndpoinds.DELETE_GROUP.getEndpoint(),
                                JwtEndpoinds.CREATE_MESSAGE.getEndpoint(),
                                JwtEndpoinds.DELETE_MESSAGE.getEndpoint(),
                                JwtEndpoinds.CREATE_NOTIFICATION.getEndpoint(),
                                JwtEndpoinds.DELETE_NOTIFICATION.getEndpoint(),
                                JwtEndpoinds.CREATE_FRIENDSHIP.getEndpoint(),
                                JwtEndpoinds.DELETE_FRIENDSHIP.getEndpoint()
                        )
                        .hasRole("ROLE_ADMIN")

                        // Permitir acceso a usuarios autenticados para las operaciones generales
                        .requestMatchers(
                                JwtEndpoinds.FOLLOW_USER.getEndpoint(),
                                JwtEndpoinds.UNFOLLOW_USER.getEndpoint(),
                                JwtEndpoinds.GET_USER_FOLLOWERS.getEndpoint(),
                                JwtEndpoinds.GET_USER_FOLLOWING.getEndpoint(),
                                JwtEndpoinds.ADD_USER_TO_ROOM.getEndpoint(),
                                JwtEndpoinds.REMOVE_USER_FROM_ROOM.getEndpoint(),
                                JwtEndpoinds.IS_ROOM_ACTIVE.getEndpoint(),
                                JwtEndpoinds.CREATE_PROFILE.getEndpoint(),
                                JwtEndpoinds.UPDATE_PROFILE.getEndpoint(),
                                JwtEndpoinds.DELETE_PROFILE.getEndpoint()
                        )
                        .authenticated()
        );
    }


    private void configureUserDetailsService(HttpSecurity http) throws Exception {
        http.userDetailsService(customerDetailsService);
    }

    private void configureSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }

    private void configureFilters(HttpSecurity http) {
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private void configureExceptionHandling(HttpSecurity http) throws Exception {
        http.exceptionHandling(exception -> exception
                .accessDeniedHandler(
                        (request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value()))
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

}
