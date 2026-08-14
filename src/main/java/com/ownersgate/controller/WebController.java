package com.ownersgate.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ownersgate.dto.WasteItemDto;
import com.ownersgate.service.SelfDisposalLogService;
import com.ownersgate.service.WasteItemService;

import lombok.RequiredArgsConstructor;

/**
 * 画面（HTML）を返す Controller。
 * データの取得は Service に任せ、受け取った DTO を画面に渡すだけ。
 */
@Controller
@RequiredArgsConstructor //コンストラクタインジェクション用のコンストラクタを自動生成する Lombok アノテーション
public class WebController {

    private final WasteItemService wasteItemService; // 廃棄アイテムの一覧取得・追加・削除・完了切り替えなどの処理を行うサービス
    private final SelfDisposalLogService logService; // 自己廃棄ログの一覧取得・追加などの処理を行うサービス

//    public WebController(WasteItemService wasteItemService, SelfDisposalLogService logService) {
//        this.wasteItemService = wasteItemService;
//        this.logService = logService;
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // トップ画面。room パラメータがあれば、その部屋だけに絞り込む。
    // 追加・削除・完了切り替えは画面側の JavaScript が REST API（/api/...）を呼ぶ。
    @GetMapping("/")
    public String dashboard(@RequestParam(required = false) Integer room, Model model) {
        List<WasteItemDto> wasteItems;
        if (room != null) {
            wasteItems = wasteItemService.selectByRoom(room);
        } else {
            wasteItems = wasteItemService.selectAll();
        }
        model.addAttribute("wasteItems", wasteItems);
        model.addAttribute("selectedRoom", room);
        model.addAttribute("logs", logService.selectAll());
        return "dashboard";
    }
}
