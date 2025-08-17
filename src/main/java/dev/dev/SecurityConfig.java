package dev.dev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 개발 중: H2 콘솔은 CSRF 예외 + sameOrigin frame 허용
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(h -> h.frameOptions(frame -> frame.sameOrigin()))

                .authorizeHttpRequests(auth -> auth
                        // 정적 리소스/공개 페이지
                        .requestMatchers("/", "/login", "/register", "/users/register",
                                "/css/**", "/js/**", "/images/**",
                                "/h2-console/**", "/error").permitAll()

                        // 게시판 화면(Thymeleaf) 조회는 공개
                        .requestMatchers(HttpMethod.GET, "/board", "/board/**").permitAll()

                        // 게시글 목록/상세 API는 공개, 생성/수정/삭제는 인증
                        .requestMatchers(HttpMethod.GET, "/posts", "/posts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/posts/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/posts/**").authenticated()

                        // 그 외는 인증
                        .anyRequest().authenticated()
                )

                .formLogin(login -> login
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true)
                )

                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }
}

