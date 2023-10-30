package com.example.fashionmanager.service.impl.module_san_pham.loaisanpham;

import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.CreateLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.ListLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request.UpdateLoaiSanPhamRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.response.LoaiSanPhamDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.response.LoaiSanPhamResponse;
import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import com.example.fashionmanager.service.CRUDService;

public interface LoaiSanPhamService extends CRUDService<LoaiSanPhamEntity, CreateLoaiSanPhamRequest, UpdateLoaiSanPhamRequest,
        ListLoaiSanPhamRequest, LoaiSanPhamResponse, LoaiSanPhamDetailResponse> {
}
