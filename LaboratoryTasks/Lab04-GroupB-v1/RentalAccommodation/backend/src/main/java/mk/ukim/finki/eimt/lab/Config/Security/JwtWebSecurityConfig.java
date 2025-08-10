package mk.ukim.finki.eimt.lab.Config.Security;

import mk.ukim.finki.eimt.lab.Config.CustomUsernamePasswordAuthenticationProvider;
import mk.ukim.finki.eimt.lab.Config.JWT.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class JwtWebSecurityConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    public JwtWebSecurityConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider, JwtFilter jwtFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer ->
                        corsCustomizer.configurationSource(corsConfigurationSource())
                )
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
                        authorizeHttpRequestsCustomizer
//                                requestMatchers(
//                                        "/swagger-ui/**",
//                                        "/v3/api-docs/**",
//                                        "/api/user/register",
//                                        "/api/user/login"
//                                )
//                                .permitAll()
//                                .requestMatchers(
//                                        "/api/categories",
//                                        "/api/categories/{id}",
//                                        "/api/manufacturers",
//                                        "/api/manufacturers/{id}",
//                                        "/api/products",
//                                        "/api/products/{id}",
//                                        "/api/shopping-cart",
//                                        "/api/shopping-cart/add-product/{id}"
//                                )
//                                .hasAnyRole("USER", "ADMIN")
//                                .anyRequest()
//                                .hasRole("ADMIN")
                                .anyRequest()
                                .permitAll()                )
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
