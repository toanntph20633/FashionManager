package com.example.fashionmanager.controller.admin.sanpham.quanlyloaisanpham;

import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.CreateLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.ListLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.UpdateLoaiSanPhamRequest;
import com.example.fashionmanager.service.impl.module_san_pham.loaisanpham.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-loai-san-pham")
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenLoai", required = false) String tenLoai
    ){
        return loaiSanPhamService.getList(ListLoaiSanPhamRequest.builder().tenLoai(tenLoai)
                .page(page).size(size).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateLoaiSanPhamRequest request){
        return loaiSanPhamService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateLoaiSanPhamRequest request){
        return loaiSanPhamService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return loaiSanPhamService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return loaiSanPhamService.delete(id);
    }
}
