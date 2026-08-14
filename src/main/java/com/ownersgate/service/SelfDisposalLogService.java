package com.ownersgate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ownersgate.dto.SelfDisposalLogDto;
import com.ownersgate.entity.SelfDisposalLog;
import com.ownersgate.exception.NotFoundException;
import com.ownersgate.repository.SelfDisposalLogRepository;

import lombok.RequiredArgsConstructor;

/**
 * 自力処分の記録の業務ロジックを担当する Service 層。
 *
 * - Controller とは DTO でやり取りする
 * - Repository とは Entity でやり取りする
 * - DTO と Entity の変換は、この層の中で行う
 *
 * クラスに @Transactional を付けると、各メソッドが「1つのまとまった処理」になり、
 * 途中で失敗したら、その処理でのDB変更は全部なかったことにされる（ロールバック）。
 * 参照だけのメソッドは readOnly = true にして無駄な負荷を減らす。
 */
@Service
@Transactional
@RequiredArgsConstructor //コンストラクタインジェクション用のコンストラクタを自動生成する Lombok アノテーション
public class SelfDisposalLogService {

    private final SelfDisposalLogRepository repository;

//    public SelfDisposalLogService(SelfDisposalLogRepository repository) {
//        this.repository = repository;
//    }

    /** 全件取得 */
    @Transactional(readOnly = true)
    public List<SelfDisposalLogDto> selectAll() {
        List<SelfDisposalLogDto> result = new ArrayList<>();
        for (SelfDisposalLog entity : repository.findAll(Sort.by(Sort.Direction.ASC, "id"))) {
            result.add(toDto(entity));
        }
        return result;
    }

    /** 1件取得（見つからなければ NotFoundException） */
    @Transactional(readOnly = true)
    public SelfDisposalLogDto selectById(Long id) {
        SelfDisposalLog entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("処分の記録が見つかりません: id=" + id));
        return toDto(entity);
    }

    /** 新規登録 */
    public SelfDisposalLogDto register(SelfDisposalLogDto dto) {
        SelfDisposalLog saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    /** 更新 */
    public SelfDisposalLogDto modify(Long id, SelfDisposalLogDto dto) {
        SelfDisposalLog entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("処分の記録が見つかりません: id=" + id));
        entity.setMainCategory(dto.getMainCategory());
        entity.setCategory(dto.getCategory());
        entity.setQuantity(dto.getQuantity());
        entity.setUnit(dto.getUnit());
        entity.setDisposalDate(dto.getDisposalDate());
        entity.setNote(dto.getNote());
        SelfDisposalLog saved = repository.save(entity);
        return toDto(saved);
    }

    /** 削除 */
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("処分の記録が見つかりません: id=" + id);
        }
        repository.deleteById(id);
    }

    // ----- DTO と Entity の変換（この層だけの仕事） -----

    /** Entity -> DTO */
    private SelfDisposalLogDto toDto(SelfDisposalLog entity) {
        SelfDisposalLogDto dto = new SelfDisposalLogDto();
        dto.setId(entity.getId());
        dto.setMainCategory(entity.getMainCategory());
        dto.setCategory(entity.getCategory());
        dto.setQuantity(entity.getQuantity());
        dto.setUnit(entity.getUnit());
        dto.setDisposalDate(entity.getDisposalDate());
        dto.setNote(entity.getNote());
        return dto;
    }

    /** DTO -> Entity */
    private SelfDisposalLog toEntity(SelfDisposalLogDto dto) {
        SelfDisposalLog entity = new SelfDisposalLog();
        entity.setId(dto.getId());
        entity.setMainCategory(dto.getMainCategory());
        entity.setCategory(dto.getCategory());
        entity.setQuantity(dto.getQuantity());
        entity.setUnit(dto.getUnit());
        entity.setDisposalDate(dto.getDisposalDate());
        entity.setNote(dto.getNote());
        return entity;
    }
}
