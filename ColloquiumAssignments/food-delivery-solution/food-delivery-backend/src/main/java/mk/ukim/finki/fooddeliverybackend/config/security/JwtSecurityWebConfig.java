package mk.ukim.finki.fooddeliverybackend.config.security;

import mk.ukim.finki.fooddeliverybackend.web.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityWebConfig {

    private final JwtFilter jwtFilter;

    public JwtSecurityWebConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("OWNER")
                .role("OWNER").implies("CUSTOMER")
                .build();
    }

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer ->
                        corsCustomizer.configurationSource(corsConfigurationSource())
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
                                authorizeHttpRequestsCustomizer
                                        .requestMatchers(
                                                "/swagger-ui/**",
                                                "/v3/api-docs/**",
                                                "/h2/**",
                                                "/api/user/register",
                                                "/api/user/login"
                                        )
                                        .permitAll()
                                        .requestMatchers(
                                                "/api/user/me"
                                        )
                                        .authenticated()
                                        .requestMatchers(
                                                "/api/restaurants",
                                                "/api/restaurants/{id}",
                                                "/api/dishes",
                                                "/api/dishes/{id}",
                                                "/api/dishes/{id}/details",
                                                "/api/dishes/{id}/add-to-order",
                                                "/api/dishes/{id}/remove-from-order",
                                                "/api/orders/pending",
                                                "/api/orders/pending/confirm",
                                                "/api/orders/pending/cancel"
                                        )
                                        .hasRole(
                                                "CUSTOMER"
                                        )
                                        .requestMatchers(
                                                "/api/dishes/add",
                                                "/api/dishes/{id}/edit",
                                                "/api/dishes/{id}/delete",
                                                "/api/restaurants/add",
                                                "/api/restaurants/{id}/edit",
                                                "/api/restaurants/{id}/delete"
                                        )
                                        .hasRole(
                                                "OWNER"
                                        )
                                        .requestMatchers("/api/users/{username}")
                                        .hasRole(
                                                "ADMIN"
                                        )
                                        .anyRequest()
                                        .hasRole(
                                                "ADMIN"
                                        )
//                                .anyRequest()
//                                .permitAll()
                )
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
