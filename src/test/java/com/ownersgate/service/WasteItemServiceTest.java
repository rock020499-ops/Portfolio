package com.ownersgate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.ownersgate.repository.WasteItemRepository;

@ExtendWith(MockitoExtension.class)
class WasteItemServiceTest {

    @Mock
    private WasteItemRepository repository;

    @InjectMocks
    private WasteItemService service;

    @Test
    void selectAll_SortをRepositoryに渡して全件取得できる() {
        when(repository.findAll(any(Sort.class))).thenReturn(List.of());

        List<?> result = service.selectAll();

        assertThat(result).isEmpty();
        verify(repository).findAll(any(Sort.class));
    }

    @Test
    void selectByRoom_部屋番号とSortをRepositoryに渡して絞り込める() {
        // findByRoomNumber(roomNumber, Sort) が呼ばれることを検証（旧 findByRoomNumberOrderByPhaseAsc から変更）
        when(repository.findByRoomNumber(eq(3), any(Sort.class))).thenReturn(List.of());

        List<?> result = service.selectByRoom(3);

        assertThat(result).isEmpty();
        verify(repository).findByRoomNumber(eq(3), any(Sort.class));
    }
}
