package com.ownersgate.repository;

import com.ownersgate.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    // ユーザー名で1件検索（ログイン時に使う）。メソッド名から自動でクエリが作られる。
    Optional<AppUser> findByUsername(String username);
}
