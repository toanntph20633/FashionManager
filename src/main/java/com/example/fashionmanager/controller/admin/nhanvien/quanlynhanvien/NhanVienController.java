package com.example.fashionmanager.controller.admin.nhanvien.quanlynhanvien;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUserCreateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;
import com.example.fashionmanager.service.impl.module_nhan_vien.nhanvien.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("super-admin/nhan-vien-manager")
@CrossOrigin(origins = "*", maxAge = -1)
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    // theieus size
    @GetMapping("list")
    public ListReponseDto<NhanVienResponse> getActiveNhanVien(
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "0") int pageSize,
            @RequestParam(value = "tenNhanVien", required = false) String tenNhanVien,
            @RequestParam(value = "cccd", required = false) String cccd,
            @RequestParam(value = "diaChi", required = false) String diaChi,
            @RequestParam(value = "gioiTinh", required = false) Boolean gioiTinh,
            @RequestParam(value = "soDienThoai", required = false) String soDienThoai,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "active", required = false) Boolean active,
            @RequestParam(value = "deleted",required = false) Boolean deleted

    ){
        return nhanVienService.getActiveEmployees(pageIndex,pageSize,id,tenNhanVien,cccd,soDienThoai,diaChi,gioiTinh,active,deleted);
    }

    @PostMapping("create")
    public ResponseDto<NhanVienResponse> create(
            @RequestBody @Valid NhanVienUserCreateRequest nhanVienUserCreateRequest, BindingResult bindingResult
            ){
        if (bindingResult.hasErrors()){
            throw new RuntimeException("Thông tin không hợp lệ");
        }

        return nhanVienService.save(nhanVienUserCreateRequest);
    }

    @GetMapping("detail/{id}")
    public ResponseDto<NhanVienResponse> detail(@PathVariable Long id) {
        return nhanVienService.detail(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<NhanVienResponse> update(@PathVariable Long id, @RequestBody @Valid NhanVienUpdateRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Thiếu thông tin");
        }
        request.setId(id);
        return nhanVienService.update(request);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<NhanVienResponse> delete(@PathVariable Long id) {
        return nhanVienService.delete(id);
    }
    @PutMapping("change-active/{id}")
    public ResponseDto<NhanVienResponse> changeActive(@PathVariable Long id) {
        return nhanVienService.changeActive(id);
    }
}
