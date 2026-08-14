package com.ownersgate.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity // JPA の Entity であることを示すアノテーション
@Data //
public class SelfDisposalLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: DB側で自動採番される（MySQL の AUTO_INCREMENT と同じ）
    private Long id;

    private String mainCategory; // 大分類: 燃えるゴミ / その他不燃ゴミ / 缶ペットボトル / ビン / プラゴミ / 紙資源・古着
    private String category;     // 小分類: 生ゴミ / 紙屑 / 本 / 雑誌 / 段ボール etc.
    private Integer quantity;    // 数量
    private String unit;         // 単位: 袋 / 束 / 個
    private LocalDate disposalDate; // 自力処分日
    private String note;         // 備考

    // Getters and Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getMainCategory() { return mainCategory; }
//    public void setMainCategory(String mainCategory) { this.mainCategory = mainCategory; }
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//
//    public Integer getQuantity() { return quantity; }
//    public void setQuantity(Integer quantity) { this.quantity = quantity; }
//
//    public String getUnit() { return unit; }
//    public void setUnit(String unit) { this.unit = unit; }
//
//    public LocalDate getDisposalDate() { return disposalDate; }
//    public void setDisposalDate(LocalDate disposalDate) { this.disposalDate = disposalDate; }
//
//    public String getNote() { return note; }
//    public void setNote(String note) { this.note = note; }
}
