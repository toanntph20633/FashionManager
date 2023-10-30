package com.example.fashionmanager.controller.admin.sanpham.quanlyloplot;

import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.service.impl.module_san_pham.loplot.LopLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-lop-lot")
public class LopLotController {
    @Autowired
    private LopLotService lopLotService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenLopLot", required = false) String tenLopLot
    ){
        return lopLotService.getList(ListLopLotRequest.builder().tenLopLot(tenLopLot)
                .page(page).size(size).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateLopLotRequest request){
        return lopLotService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateLopLotRequest request){
        return lopLotService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return lopLotService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return lopLotService.delete(id);
    }
}
