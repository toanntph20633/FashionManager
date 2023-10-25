package com.example.fashionmanager.controller.admin.sanpham.quanlyveao;

import com.example.fashionmanager.dto.sanpham.quanlyveao.request.CreateVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.ListVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.UpdateVeAoRequest;
import com.example.fashionmanager.service.impl.module_san_pham.veao.VeAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-ve-ao")
public class VeAoController {
    @Autowired
    private VeAoService veAoService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenVeAo", required = false) String tenVeAo) {
        return veAoService.getList(ListVeAoRequest.builder().tenVeAo(tenVeAo).size(size).page(page).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateVeAoRequest request) {
        //validation

        return veAoService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateVeAoRequest request) {
        //validation

        return veAoService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return veAoService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return veAoService.delete(id);
    }
}

