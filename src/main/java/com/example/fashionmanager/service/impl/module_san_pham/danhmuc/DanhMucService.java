package com.example.fashionmanager.service.impl.module_san_pham.danhmuc;

import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.CreateDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.ListDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request.UpdateDanhMucRequest;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.response.DanhMucDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlydanhmuc.response.DanhMucResponse;
import com.example.fashionmanager.entity.DanhMucEntity;
import com.example.fashionmanager.service.CRUDService;

public interface DanhMucService extends CRUDService<DanhMucEntity, CreateDanhMucRequest, UpdateDanhMucRequest,
        ListDanhMucRequest, DanhMucResponse, DanhMucDetailResponse> {
}
