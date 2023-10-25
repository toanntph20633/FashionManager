package com.example.fashionmanager.controller.admin.sanpham.quanlykieutui;

import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.CreateKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.ListKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.UpdateKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.service.impl.module_san_pham.kieutui.KieuTuiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-kieu-tui")
public class KieuTuiController {
    @Autowired
    private KieuTuiService kieuTuiService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenKieuTui", required = false) String tenKieuTui
    ){
        return kieuTuiService.getList(ListKieuTuiRequest.builder().tenKieuTui(tenKieuTui)
                .page(page).size(size).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateKieuTuiRequest request){
        return kieuTuiService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateKieuTuiRequest request){
        return kieuTuiService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return kieuTuiService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return kieuTuiService.delete(id);
    }
}
