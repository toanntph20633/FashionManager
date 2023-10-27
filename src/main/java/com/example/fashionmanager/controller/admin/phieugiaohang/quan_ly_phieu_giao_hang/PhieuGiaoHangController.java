package com.example.fashionmanager.controller.admin.phieugiaohang.quan_ly_phieu_giao_hang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangListRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangCreateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangListRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangUpdateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.respones.PhieuGiaoHangRespones;
import com.example.fashionmanager.service.impl.module.phieu_giao_hang.phieugiaohang.PhieuGiaoHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("super-admin/phieu-giao-hang-manager")
public class PhieuGiaoHangController {
    @Autowired
    private PhieuGiaoHangService service;
    @GetMapping("/list")
    public ListReponseDto<PhieuGiaoHangRespones> getActive(
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "maPhieuGiao", required = false) String maPhieuGiao,
            @RequestParam(value = "tenNguoiNhan", required = false) String tenNguoiNhan,
            @RequestParam(value = "tenNguoiGiao", required = false) String tenNguoiGiao
    ){
        PhieuGiaoHangListRequest request = PhieuGiaoHangListRequest.builder()
                .page(pageIndex)
                .size(size)
                .maPhieuGiao(maPhieuGiao)
                .tenNguoiGiao(tenNguoiGiao)
                .tenNguoiNhan(tenNguoiNhan)
                .build();
        return service.getAll(request);
    }

    @PostMapping("/add")
    public ResponseDto<PhieuGiaoHangRespones> add(@RequestBody @Valid PhieuGiaoHangCreateRequest request, BindingResult result){
        if(result.hasErrors()){
            throw new RuntimeException("Thông tin không hợp lệ!");
        }
        return service.save(request);
    }


    @GetMapping("/detail/{id}")
    public ResponseDto<PhieuGiaoHangRespones> detail(@PathVariable Long id){
        return service.detail(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDto<PhieuGiaoHangRespones> update(@PathVariable Long id, @RequestBody @Valid PhieuGiaoHangUpdateRequest  request, BindingResult result){
        if (result.hasErrors()){
            throw new RuntimeException(("Không để trống thông tin!"));
        }
        request.setId(id);
        return service.update(request);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<PhieuGiaoHangRespones>delete(@PathVariable Long id){
        return service.delete(id);
    }

    public ResponseDto<PhieuGiaoHangRespones>changeActive(@PathVariable Long id){
        return service.changActive(id);
    }


}
