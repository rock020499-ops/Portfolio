package com.ownersgate.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ownersgate.dto.WasteItemDto;
import com.ownersgate.service.WasteItemService;

import lombok.RequiredArgsConstructor;

/**
 * 廃棄アイテムの REST API（JSON を返す/受け取る）。
 * DBには直接触れず、すべて Service に任せる。
 * Controller 層のメソッド名は findAll / findById など。
 */
@RestController
@RequestMapping("/api/waste-items")
@RequiredArgsConstructor //コンストラクタインジェクション用のコンストラクタを自動生成する Lombok アノテーション
public class WasteItemRestController {

    private final WasteItemService service;

//    public WasteItemRestController(WasteItemService service) {
//        this.service = service;
//    }

    // 全件取得
    @GetMapping
    public List<WasteItemDto> findAll() {
        return service.selectAll();
    }

    // 1件取得
    @GetMapping("/{id}")
    public WasteItemDto findById(@PathVariable Long id) {
        return service.selectById(id);
    }

    // 新規登録
    @PostMapping
    public WasteItemDto create(@Valid @RequestBody WasteItemDto dto) {
        return service.register(dto);
    }

    // 更新
    @PutMapping("/{id}")
    public WasteItemDto update(@PathVariable Long id, @Valid @RequestBody WasteItemDto dto) {
        return service.modify(id, dto);
    }

    // 完了 / 未完了を切り替え
    @PutMapping("/{id}/toggle")
    public WasteItemDto toggle(@PathVariable Long id) {
        return service.toggleStatus(id);
    }

    // 削除
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
