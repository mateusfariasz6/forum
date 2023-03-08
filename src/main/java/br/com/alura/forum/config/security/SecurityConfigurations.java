package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET, "/topicos")
                        .permitAll())
                .httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET, "/topicos/*"))
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }


}
