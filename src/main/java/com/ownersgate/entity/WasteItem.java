package com.ownersgate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity // JPA の Entity であることを示すアノテーション
@Data // Lombok の @Data アノテーションを使うと、Getter/Setter/コンストラクタなどが自動生成される
public class WasteItem {

    @Id // 主キーであることを示すアノテーション
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: DB側で自動採番される（MySQL の AUTO_INCREMENT と同じ）
    private Long id;

    private Integer roomNumber; // 部屋番号
    private String itemName;    // 廃棄アイテム名
    private String size;        // サイズ（小・中・大など）
    private Integer phase;      // 廃棄の段階（1: まだ廃棄していない / 2: 廃棄済み / 3: 自力処分済み）
    private Boolean isCompleted = false; // 廃棄完了フラグ（true: 廃棄完了 / false: 廃棄未完了）

    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    
//    public Integer getRoomNumber() { return roomNumber; }
//    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }
//
//    public String getItemName() { return itemName; }
//    public void setItemName(String itemName) { this.itemName = itemName; }
//
//    public String getSize() { return size; }
//    public void setSize(String size) { this.size = size; }
//
//    public Integer getPhase() { return phase; }
//    public void setPhase(Integer phase) { this.phase = phase; }
//
//    public Boolean getIsCompleted() { return isCompleted; }
//    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}
