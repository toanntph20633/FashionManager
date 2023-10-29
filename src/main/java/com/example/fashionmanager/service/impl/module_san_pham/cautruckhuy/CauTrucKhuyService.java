package com.example.fashionmanager.service.impl.module_san_pham.cautruckhuy;

import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.CreateCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.ListCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request.UpdateCauTrucKhuyRequest;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.response.CauTrucKhuyDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.response.CauTrucKhuyResponse;
import com.example.fashionmanager.entity.CauTrucKhuyEntity;
import com.example.fashionmanager.service.CRUDService;

public interface CauTrucKhuyService extends CRUDService<CauTrucKhuyEntity, CreateCauTrucKhuyRequest, UpdateCauTrucKhuyRequest,
        ListCauTrucKhuyRequest, CauTrucKhuyResponse, CauTrucKhuyDetailResponse> {
}
