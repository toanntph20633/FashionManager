package com.example.fashionmanager.service.impl.module_khach_hang.khach_hang;

import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.responst.KhachHangResponse;
import com.example.fashionmanager.entity.KhachHangEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {
    @Mapping(source = "hangId", target = "hangEntity.id")
    KhachHangEntity getKhachHangEntity(KhachHangCreateResquest createResquest);
    KhachHangEntity getKhachHangEntity(KhachHangUpdateRequest updateRequest);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "maKhachHang", target = "maKhachHang")
    @Mapping(source = "tenKhachHang", target = "tenKhachHang")
    @Mapping(source = "soDienThoai", target = "soDienThoai")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "ngaySinh", target = "ngaySinh")
    @Mapping(source = "diemTichLuy", target = "diemTichLuy")
    @Mapping(source = "hangEntity.tenHang", target = "tenHang")
    KhachHangResponse getKhachHangResponse(KhachHangEntity khachHangEntity);
}
