package com.ownersgate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ownersgate.dto.WasteItemDto;
import com.ownersgate.entity.WasteItem;
import com.ownersgate.exception.NotFoundException;
import com.ownersgate.repository.WasteItemRepository;

/**
 * 廃棄アイテムの業務ロジックを担当する Service 層。
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
public class WasteItemService {

    private final WasteItemRepository repository;

    public WasteItemService(WasteItemRepository repository) {
        this.repository = repository;
    }

    /** 全件取得 */
    @Transactional(readOnly = true)
    public List<WasteItemDto> selectAll() {
        List<WasteItemDto> result = new ArrayList<>();
        for (WasteItem entity : repository.findAll(
        		Sort.by(Sort.Direction.ASC, "roomNumber", "phase", "isCompleted", "id"))) {
            result.add(toDto(entity));
        }
        return result;
    }

    /** 部屋番号で絞り込んで取得 */
    @Transactional(readOnly = true)
    public List<WasteItemDto> selectByRoom(Integer roomNumber) {
        List<WasteItemDto> result = new ArrayList<>();
        for (WasteItem entity : repository.findByRoomNumber(roomNumber,
                Sort.by(Sort.Direction.ASC, "phase", "isCompleted", "id"))) {
            result.add(toDto(entity));
        }
        return result;
    }

    /** 1件取得（見つからなければ NotFoundException） */
    @Transactional(readOnly = true)
    public WasteItemDto selectById(Long id) {
        WasteItem entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("廃棄アイテムが見つかりません: id=" + id));
        return toDto(entity);
    }

    /** 新規登録 */
    public WasteItemDto register(WasteItemDto dto) {
        WasteItem entity = toEntity(dto);
        entity.setIsCompleted(false); // 新規は必ず未完了で登録
        WasteItem saved = repository.save(entity);
        return toDto(saved);
    }

    /** 更新 */
    public WasteItemDto modify(Long id, WasteItemDto dto) {
        WasteItem entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("廃棄アイテムが見つかりません: id=" + id));
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setItemName(dto.getItemName());
        entity.setSize(dto.getSize());
        entity.setPhase(dto.getPhase());
        WasteItem saved = repository.save(entity);
        return toDto(saved);
    }

    /** 完了 / 未完了を切り替える */
    public WasteItemDto toggleStatus(Long id) {
        WasteItem entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("廃棄アイテムが見つかりません: id=" + id));
        entity.setIsCompleted(!entity.getIsCompleted());
        WasteItem saved = repository.save(entity);
        return toDto(saved);
    }

    /** 削除 */
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("廃棄アイテムが見つかりません: id=" + id);
        }
        repository.deleteById(id);
    }

    // ----- DTO と Entity の変換（この層だけの仕事） -----

    /** Entity -> DTO */
    private WasteItemDto toDto(WasteItem entity) {
        WasteItemDto dto = new WasteItemDto();
        dto.setId(entity.getId());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setItemName(entity.getItemName());
        dto.setSize(entity.getSize());
        dto.setPhase(entity.getPhase());
        dto.setIsCompleted(entity.getIsCompleted());
        return dto;
    }

    /** DTO -> Entity */
    private WasteItem toEntity(WasteItemDto dto) {
        WasteItem entity = new WasteItem();
        entity.setId(dto.getId());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setItemName(dto.getItemName());
        entity.setSize(dto.getSize());
        entity.setPhase(dto.getPhase());
        entity.setIsCompleted(dto.getIsCompleted());
        return entity;
    }
}
