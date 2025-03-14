package com.glowcorner.backend.security;

import com.glowcorner.backend.entity.mongoDB.User;
import com.glowcorner.backend.repository.UserRepository;
import com.glowcorner.backend.utils.JwtUtilHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {

    @Autowired
    private CustomJwtFilter customJwtFilter;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtUtilHelper jwtUtilHelper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] publicUrls = {
                "/login.html", "/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                "/swagger-ui-custom", "/oauth2/authorization/google", "/login/oauth2/code/google",
                "/login/google", "/login/firebase", "/v3/api-docs/**" // Both login endpoints are public
        };

        String[] adminUrls = {"/api/users/**", "/api/admin/users/**"};

        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(publicUrls).permitAll()
                .requestMatchers(adminUrls).hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authEndpoint -> authEndpoint
                                .authorizationRequestRepository(new HttpSessionOAuth2AuthorizationRequestRepository()))
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService()))
                        .successHandler((request, response, authentication) -> {
                            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
                            String email = oidcUser.getEmail();

                            User user = userRepository.findByEmail(email);
                            if (user == null) {
                                response.sendRedirect("/login.html?error=user_not_found");
                                return;
                            }

                            String jwtToken = jwtUtilHelper.generateToken(email);
                            response.sendRedirect("/app/index.html?token=" + jwtToken + "&userType=MANAGER");
                        })
                        .failureHandler((request, response, exception) -> {
                            response.sendRedirect("/login.html?error=authentication_failed");
                        })
                );

        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        OidcUserService delegate = new OidcUserService();

        return userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);
            String email = oidcUser.getEmail();

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User with email " + email + " not found in the system");
            }

            List<SimpleGrantedAuthority> authorities = oidcUser.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                    .collect(Collectors.toList());

            authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));

            return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

