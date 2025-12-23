package br.com.vestris.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF para API
                .authorizeHttpRequests(auth -> auth
                        // Libera Swagger e rotas de Auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/v1/auth/**" // Login e Registro livres
                        ).permitAll()
                        // Por enquanto, libera tudo para facilitar os testes das outras fases
                        // Depois vamos bloquear: .anyRequest().authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
