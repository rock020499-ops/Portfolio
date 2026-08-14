package com.ownersgate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 廃棄アイテムの「受け渡し用」の箱（DTO）。
 * 画面やREST API（Controller）と Service の間でやり取りするために使う。
 * DB用の Entity（WasteItem）とはあえて分けている。
 *
 * 各フィールドに付けた @NotBlank などが「入力チェックのルール」。
 * Controller で @Valid を付けて受け取ると、このルールが自動でチェックされる。
 */
@Data // Lombok の @Data アノテーションを使うと、Getter/Setter/コンストラクタなどが自動生成される
public class WasteItemDto {

    private Long id;

    @NotNull(message = "部屋は必須です")
    private Integer roomNumber;

    @NotBlank(message = "アイテム名は必須です")
    @Size(max = 255, message = "アイテム名は255文字以内で入力してください")
    private String itemName;

    @NotBlank(message = "サイズは必須です")
    private String size;

    @NotNull(message = "Phaseは必須です")
    private Integer phase;

    private Boolean isCompleted;

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
