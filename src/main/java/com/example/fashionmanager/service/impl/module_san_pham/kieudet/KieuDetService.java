package com.example.fashionmanager.service.impl.module_san_pham.kieudet;

import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.CreateKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.ListKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.request.UpdateKieuDetRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.response.KieuDetDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudet.response.KieuDetResponse;
import com.example.fashionmanager.entity.KieuDetEntity;
import com.example.fashionmanager.service.CRUDService;

public interface KieuDetService extends CRUDService<KieuDetEntity, CreateKieuDetRequest, UpdateKieuDetRequest,
        ListKieuDetRequest, KieuDetResponse, KieuDetDetailResponse> {
}
