## src\main\java\br\com\vestris\portal\config

### JacksonConfiguration.java

```java
// src\main\java\br\com\vestris\portal\config\JacksonConfiguration.java
package br.com.vestris.portal.config;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
    @Bean
    public JsonNullableModule jsonNullableModule() {
        return new JsonNullableModule();
    }
}

```

### SecurityConfig.java

```java
// src\main\java\br\com\vestris\portal\config\SecurityConfig.java
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

```

### WebConfig.java

```java
// src\main\java\br\com\vestris\portal\config\WebConfig.java
package br.com.vestris.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

```

