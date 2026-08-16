package com.ownersgate.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ownersgate.config.SecurityConfig;
import com.ownersgate.service.AppUserDetailsService;
import com.ownersgate.service.WasteItemService;

@WebMvcTest(value = WasteItemRestController.class, excludeAutoConfiguration = UserDetailsServiceAutoConfiguration.class)
@Import(SecurityConfig.class)
class WasteItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WasteItemService service;

    // SecurityConfig が必要とする UserDetailsService をモック化して提供する
    @MockitoBean
    private AppUserDetailsService appUserDetailsService;

    @Test
    @WithMockUser
    void findAll_ログイン済みで200が返る() throws Exception {
        when(service.selectAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/waste-items"))
               .andExpect(status().isOk());
    }

    @Test
    void findAll_未ログインで302リダイレクトが返る() throws Exception {
        mockMvc.perform(get("/api/waste-items"))
               .andExpect(status().is3xxRedirection());
    }
}
