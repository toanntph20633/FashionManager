package com.example.fashionmanager.controller.admin.sanpham.quanlyxeta;

import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.CreateXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.ListXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.UpdateXeTaRequest;
import com.example.fashionmanager.service.impl.module_san_pham.xeta.XeTaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-xe-ta")
public class XeTaController {
    @Autowired
    private XeTaService xeTaService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenXeTa", required = false) String tenXeTa) {
        return xeTaService.getList(ListXeTaRequest.builder().tenXeTa(tenXeTa).size(size).page(page).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateXeTaRequest request) {
        //validation

        return xeTaService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateXeTaRequest request) {
        //validation

        return xeTaService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return xeTaService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return xeTaService.delete(id);
    }
}
