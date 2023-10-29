package com.example.fashionmanager.service.impl.module_san_pham.kieudang;

import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.CreateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.ListKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.request.UpdateKieuDangRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.service.CRUDService;


public interface KieuDangService extends CRUDService<KieuDangEntity, CreateKieuDangRequest, UpdateKieuDangRequest
        , ListKieuDangRequest, KieuDangResponse, KieuDangDetailResponse>{
    
}
