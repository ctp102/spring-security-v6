package com.example.springsecurity6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (API 서버에선 일반적으로 필요 없음)
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // 모든 요청 허용
//                );
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // HttpSecurity 객체는 HttpSecurityConfiguration.httpSecurity()에서 생성된 빈을 주입 받는다

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/", true)  // true : 로그인 성공하면 무조건 /으로 이동. false : 로그인 성공 시 만약 이전에 인증이 실패되어 넘어온 로그인이었다면 이전 요청 경로로 강제 이동
                        .failureUrl("/failed")
                        .usernameParameter("userId")
                        .passwordParameter("passwd")
                        .successHandler((request, response, authentication) -> {
                            System.out.println("authentication: " + authentication);
                            response.sendRedirect("/home");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception: " + exception.getMessage());
                            response.sendRedirect("/login");
                        })
                        .permitAll())
                .exceptionHandling(exception -> exception
                        // authenticationEntryPoint :: '인증되지 않은 사용자'가 보호된 리소스에 접근할 때 어떻게 응답할지 정의하는 클래스
                        .authenticationEntryPoint((request, response, authException) -> {
                            System.out.println("exception: " + authException.getMessage());
                            response.sendRedirect("/login");
                        })
                        // accessDeniedHandler :: '인증은 되었지만, 권한이 없는 사용자'가 보호된 리소스에 접근할 때 어떻게 응답할지 정의하는 클래스
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println("exception: " + accessDeniedException.getMessage());
                            response.sendRedirect("/denied");
                        })
                );

        // 이 시점에 http 객체는 여러 개의 configurer 객체들을 갖고 있는 상태이다.
        // http.build 메서드를 호출하면 AbstractSecurityBuilder.build() 의해 init() -> configure() -> performBuild() 순으로 호출된다. (HttpSecurity)
            // 1. init() ->
            // 2. configure() -> 필터 생성
            // 3. perfomBuild() -> 생성된 필터들을 정렬하고 DefaultSecurityFilterChain을 생성한다.
        // 이후 WebSecurity도 위와 같은 과정을 거친다.
            // 1. init()
            // 2. configure()
            // 3. performBuild() -> securityFilterChains 목록 기반으로 FilterChainProxy 생성
        return http.build(); // 결론적으로 이 메서드 호출 시 최종적으로 FilterChainProxy 객체가 생성된다.
    }

}
