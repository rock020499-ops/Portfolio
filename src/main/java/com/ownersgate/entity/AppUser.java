package com.ownersgate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 * ログインユーザーを表す Entity（app_user テーブルに対応）。
 * password はハッシュ化された文字列を保持する（平文は入れない）。
 * ※ Spring Security の User クラスと名前が衝突しないよう AppUser とした。
 */
@Entity // JPA の Entity であることを示すアノテーション
@Table(name = "app_user") // テーブル名を指定するアノテーション（省略するとクラス名がテーブル名になる）
@Data // Lombok の @Data アノテーションを使うと、Getter/Setter/コンストラクタなどが自動生成される
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // IDENTITY: DB側で自動採番される（MySQL の AUTO_INCREMENT と同じ）

    private String username; // ユーザー名（ログインIDとして使う）
    private String password; // BCrypt でハッシュ化された値
    private String role;     // 権限（ROLE_USER / ROLE_ADMIN など）

    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//
//    public String getRole() { return role; }
//    public void setRole(String role) { this.role = role; }
}
