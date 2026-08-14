package com.ownersgate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/css/**", "/js/**").permitAll()
                //.requestMatchers("/h2-console/**").permitAll() // 開発用：H2コンソールはログインなしで見られるようにする
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)
            )
            .logout((logout) -> logout.permitAll())
            .csrf(csrf -> csrf.disable()); // Disabled for simplicity in local REST MVP

        // Allow H2 console for debugging
        //http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }

    // パスワードのハッシュ化・照合に使う。
    // ログイン時、入力パスワードをこの方式でハッシュ化し、DBのハッシュと突き合わせる。
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ※ ユーザー情報は AppUserDetailsService（DBのapp_userテーブル）から取得する。
    //    以前ここにあった InMemoryUserDetailsManager（メモリ上に平文で1人固定）は廃止した。
}
