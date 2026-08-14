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

import com.ownersgate.dto.SelfDisposalLogDto;
import com.ownersgate.service.SelfDisposalLogService;

import lombok.RequiredArgsConstructor;

/**
 * 自力処分の記録の REST API（JSON を返す/受け取る）。
 * DBには直接触れず、すべて Service に任せる。
 * Controller 層のメソッド名は findAll / findById など。
 */
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor //コンストラクタインジェクション用のコンストラクタを自動生成する Lombok アノテーション
public class SelfDisposalLogRestController {

    private final SelfDisposalLogService service;

//    public SelfDisposalLogRestController(SelfDisposalLogService service) {
//        this.service = service;
//    }

    // 全件取得
    @GetMapping
    public List<SelfDisposalLogDto> findAll() {
        return service.selectAll();
    }

    // 1件取得
    @GetMapping("/{id}")
    public SelfDisposalLogDto findById(@PathVariable Long id) {
        return service.selectById(id);
    }

    // 新規登録
    @PostMapping
    public SelfDisposalLogDto create(@Valid @RequestBody SelfDisposalLogDto dto) {
        return service.register(dto);
    }

    // 更新
    @PutMapping("/{id}")
    public SelfDisposalLogDto update(@PathVariable Long id, @Valid @RequestBody SelfDisposalLogDto dto) {
        return service.modify(id, dto);
    }

    // 削除
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.remove(id);
    }
}
