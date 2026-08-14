package com.ownersgate.repository;

import com.ownersgate.entity.WasteItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WasteItemRepository extends JpaRepository<WasteItem, Long> {
    List<WasteItem> findByRoomNumber(Integer roomNumber, Sort sort);
}
