package com.example.fashionmanager.service.impl.module_khach_hang.khach_hang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangListRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.request.KhachHangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlykhachhang.responst.KhachHangResponse;

public interface KhachHangService {
    ListReponseDto<KhachHangResponse> getList(KhachHangListRequest request);
    ResponseDto<KhachHangResponse> getByID(Long id);
    ResponseDto<KhachHangResponse> create(KhachHangCreateResquest request);
    ResponseDto<KhachHangResponse> update(KhachHangUpdateRequest request);
    ResponseDto<KhachHangResponse> delete(Long id);
    ResponseDto<KhachHangResponse> changeActive(Long id);
}
