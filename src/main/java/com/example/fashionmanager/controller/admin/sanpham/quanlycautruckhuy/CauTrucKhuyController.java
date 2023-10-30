package com.example.fashionmanager.controller.admin.sanpham.quanlycautruckhuy;

import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.CreateCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.ListCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.UpdateCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.service.impl.module_san_pham.cautruckhuy.CauTrucKhuyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/quan-ly-cau-truc-khuy")
public class CauTrucKhuyController {
    @Autowired
    private CauTrucKhuyService cauTrucKhuyService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "tenCauTrucKhuy", required = false) String tenCauTrucKhuy
    ){
        return cauTrucKhuyService.getList(ListCauTrucKhuyRequest.builder()
                .tenCauTrucKhuy(tenCauTrucKhuy)
                .page(page).size(size).build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCauTrucKhuyRequest request){
        return cauTrucKhuyService.create(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateCauTrucKhuyRequest request){
        return cauTrucKhuyService.update(request);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return cauTrucKhuyService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return cauTrucKhuyService.delete(id);
    }
}
