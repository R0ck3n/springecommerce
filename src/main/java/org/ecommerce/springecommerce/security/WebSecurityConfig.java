package org.ecommerce.springecommerce.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final String[] PUBLIC_URLS = {
            "/", "/home", "/produits", "/produits/**",
            "/css/**", "/js/**", "/images/**", "/webjars/**", "/register"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/clients/**").hasRole("ADMIN")
                        .requestMatchers("/commande/regler/**").authenticated()
                        .requestMatchers("/profile").authenticated()
                        .requestMatchers("/panier/**").authenticated()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ERROR).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Vue personnalisée
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/home") // Redirection après logout
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
