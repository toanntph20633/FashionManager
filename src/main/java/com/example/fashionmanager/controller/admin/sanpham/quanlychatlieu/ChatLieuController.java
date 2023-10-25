package com.example.fashionmanager.controller.admin.sanpham.quanlychatlieu;

import com.example.fashionmanager.dto.sanpham.quanlychatlieu.request.CreateChatLieuRequest;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.request.ListChatLieuRequest;
import com.example.fashionmanager.dto.sanpham.quanlychatlieu.request.UpdateChatLieuRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.service.impl.module_san_pham.chatlieu.ChatLieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-chat-lieu")
public class ChatLieuController {
    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenChatLieu", required = false) String tenChatLieu) {
        return chatLieuService.getList(ListChatLieuRequest.builder().tenChatLieu(tenChatLieu)
                .page(page).size(size).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateChatLieuRequest request) {
        //validation

        return chatLieuService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateChatLieuRequest request) {
        //validation

        return chatLieuService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return chatLieuService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return chatLieuService.delete(id);
    }
}

