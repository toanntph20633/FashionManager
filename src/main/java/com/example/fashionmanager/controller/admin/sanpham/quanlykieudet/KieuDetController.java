package com.example.fashionmanager.controller.admin.sanpham.quanlykieudet;

import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.CreateKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.ListKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.UpdateKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.service.impl.module_san_pham.kieudet.KieuDetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-kieu-det")
public class KieuDetController {
    @Autowired
    private KieuDetService kieuDetService;
    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenKieuDet", required = false) String tenKieuDet
    ){
        return kieuDetService.getList(ListKieuDetRequest.builder().tenKieuDet(tenKieuDet).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateKieuDetRequest request){
        return kieuDetService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateKieuDetRequest request){
        return kieuDetService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return kieuDetService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return kieuDetService.delete(id);
    }
}
