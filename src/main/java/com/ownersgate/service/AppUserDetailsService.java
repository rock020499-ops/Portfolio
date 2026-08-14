package com.ownersgate.service;

import com.ownersgate.entity.AppUser;
import com.ownersgate.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ログイン時に、Spring Security から呼ばれるクラス。
 * 入力されたユーザー名で app_user テーブルを検索し、
 * 見つかったユーザー情報（ハッシュ済みパスワードを含む）を Security に渡す。
 * 実際のパスワード照合（ハッシュ比較）は Spring Security が PasswordEncoder を使って行う。
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    public AppUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + username));

        // DBの情報から、Security が扱う UserDetails を組み立てて返す
        return User.withUsername(appUser.getUsername())
                .password(appUser.getPassword()) // ハッシュ済みの値をそのまま渡す
                .roles(appUser.getRole())
                .build();
    }
}
