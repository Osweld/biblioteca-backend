package sv.edu.ues.bibliotecabackend.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sv.edu.ues.bibliotecabackend.auth.filters.JWTAuthenticationFilter;
import sv.edu.ues.bibliotecabackend.auth.filters.JWTAuthorizationFilter;
import sv.edu.ues.bibliotecabackend.auth.services.JWTService;
import sv.edu.ues.bibliotecabackend.service.UserDetailsServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JWTService jwtService, BCryptPasswordEncoder passwordEncoder, AuthenticationConfiguration authenticationConfiguration) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/swagger-ui/**", "/bus/v3/api-docs/**");
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults()) // Habilitar CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/**",
                                "/swagger-ui/**",
                                "/bus/v3/api-docs/**",
                                "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add custom filters
        httpSecurity.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtService));
        httpSecurity.addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(), jwtService));

        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
