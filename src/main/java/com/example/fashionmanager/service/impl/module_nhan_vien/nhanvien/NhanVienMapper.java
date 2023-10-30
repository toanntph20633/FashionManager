package com.example.fashionmanager.service.impl.module_nhan_vien.nhanvien;

import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;
import com.example.fashionmanager.entity.NhanVienEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {
    NhanVienEntity getNhanVienEntity(NhanVienUpdateRequest nhanVienUpdateRequest);
    NhanVienResponse getNhanVienResponse(NhanVienEntity nhanVienEntity);
}
