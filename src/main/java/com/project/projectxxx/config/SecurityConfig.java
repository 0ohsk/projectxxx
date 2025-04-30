package com.project.projectxxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer :: disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/hello","/login","/save/**",
                                "/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**").permitAll()  //루트와 로그인은 모두가 접근해야함
                        .requestMatchers("/admin").hasRole("ADMIN") //특정한 롤이 있으면(여기서는 ADMIN)이 있으면 접근
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")//여러가지 롤이 있으면 접근가능 별표시는 my뒤에 어떤 경로가 와도 되게 하기 위해
                        .anyRequest().authenticated()   // 위에서 처리하지 못한 경로에 대해서 anyRequest메소드 사용 , 인증된 사용자만 접근하기 위해 authenticated
                )
                //하나의 계정으로 여러 디바이스 로그인가능하게
                .sessionManagement(auth -> auth
                        .maximumSessions(1)     //다중 로그인 허용 갯수
                        .maxSessionsPreventsLogin(true)     //다중 로그인 초과했을 떄 true: 새로운 로그인 차단 false: 기존 세션 하나 삭제
                )
                //세션 고정 공격 보호(해커가 내 세션을 탈취해갔을 떄 그냥 둘 지(none), 새로 세션을 만들지(newSession), 다른 세션으로 교체할지(changeSessionId))
                .sessionManagement(auth -> auth
                        .sessionFixation().changeSessionId()
                );
        return http.build();
    }
}
