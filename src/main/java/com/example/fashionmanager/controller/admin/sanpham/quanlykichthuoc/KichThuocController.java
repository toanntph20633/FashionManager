package com.example.fashionmanager.controller.admin.sanpham.quanlykichthuoc;

import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request.CreateKichThuocRequest;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request.ListKichThuocRequest;
import com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request.UpdateKichThuocRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.service.impl.module_san_pham.kichthuoc.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-kich-thuoc")
public class KichThuocController {
    @Autowired
    private KichThuocService kichThuocService;
    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenKichThuoc", required = false) String tenKichThuoc) {
        return kichThuocService.getList(ListKichThuocRequest.builder()
                .tenKichThuoc(tenKichThuoc).size(size).page(page).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateKichThuocRequest request) {
        //validation

        return kichThuocService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateKichThuocRequest request) {
        //validation

        return kichThuocService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return kichThuocService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return kichThuocService.delete(id);
    }
}
