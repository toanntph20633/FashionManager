package com.example.fashionmanager.controller.admin.sanpham.quanlyhoatiet;

import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.CreateHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.ListHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.UpdateHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.service.impl.module_san_pham.hoatiet.HoaTietService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-hoa-tiet")
public class HoaTietController {
    @Autowired
    private HoaTietService hoaTietService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenHoaTiet", required = false) String tenHoaTiet
    ){
        return hoaTietService.getList(ListHoaTietRequest.builder().tenHoaTiet(tenHoaTiet).build());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateHoaTietRequest request){
        return hoaTietService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateHoaTietRequest request){
        return hoaTietService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return hoaTietService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return hoaTietService.delete(id);
    }vây
}
