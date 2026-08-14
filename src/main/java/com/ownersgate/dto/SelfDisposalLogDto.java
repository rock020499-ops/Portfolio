package com.ownersgate.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

/**
 * 自力処分の記録の「受け渡し用」の箱（DTO）。
 * 画面やREST API（Controller）と Service の間でやり取りするために使う。
 * DB用の Entity（SelfDisposalLog）とはあえて分けている。
 *
 * 各フィールドに付けた @NotBlank などが「入力チェックのルール」。
 * Controller で @Valid を付けて受け取ると、このルールが自動でチェックされる。
 */
@Data // Lombok の @Data アノテーションを使うと、Getter/Setter/コンストラクタなどが自動生成される
public class SelfDisposalLogDto {

    private Long id;

    @NotBlank(message = "大分類は必須です")
    private String mainCategory;

    @NotBlank(message = "小分類は必須です")
    private String category;

    @NotNull(message = "数量は必須です")
    @Min(value = 1, message = "数量は1以上で入力してください")
    private Integer quantity;

    @NotBlank(message = "単位は必須です")
    private String unit;

    @NotNull(message = "日付は必須です")
    private LocalDate disposalDate;

    private String note;

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
