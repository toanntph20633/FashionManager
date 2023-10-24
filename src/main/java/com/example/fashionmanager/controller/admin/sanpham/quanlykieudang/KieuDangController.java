package com.example.fashionmanager.controller.admin.sanpham.quanlykieudang;

import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.service.impl.module_san_pham.kieudang.KieuDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-kieu-dang")
public class KieuDangController {
    @Autowired
    private KieuDangService kieuDangService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "ten", required = false) String ten) {
        return kieuDangService.getList(ListKieuDangRequest.builder().tenKieuDang(ten).size(size).page(page).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateKieuDangRequest request) {
        //validation

        return kieuDangService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateKieuDangRequest request) {
        //validation

        return kieuDangService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return kieuDangService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return kieuDangService.delete(id);
    }
}
