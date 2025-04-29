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
                .formLogin(auth -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/save","/hello").permitAll()  //루트와 로그인은 모두가 접근해야함
                        .requestMatchers("/admin").hasRole("ADMIN") //특정한 롤이 있으면(여기서는 ADMIN)이 있으면 접근
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")//여러가지 롤이 있으면 접근가능 별표시는 my뒤에 어떤 경로가 와도 되게 하기 위해
                        .anyRequest().authenticated()   // 위에서 처리하지 못한 경로에 대해서 anyRequest메소드 사용 , 인증된 사용자만 접근하기 위해 authenticated
                );
        return http.build();
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception{
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder.encode("password"))
//                .roles("USER");
//    }
}
