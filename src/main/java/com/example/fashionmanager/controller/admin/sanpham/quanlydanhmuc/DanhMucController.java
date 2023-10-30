package com.example.fashionmanager.controller.admin.sanpham.quanlydanhmuc;

import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.CreateDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.ListDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.UpdateDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.service.impl.module_san_pham.danhmuc.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;
    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenDanhMuc", required = false) String tenDanhMuc) {
        return danhMucService.getList(ListDanhMucRequest.builder().tenDanhMuc(tenDanhMuc).size(size).page(page).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateDanhMucRequest request) {
        //validation

        return danhMucService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateDanhMucRequest request) {
        //validation

        return danhMucService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return danhMucService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return danhMucService.delete(id);
    }
}