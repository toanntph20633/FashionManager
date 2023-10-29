package com.example.fashionmanager.service.impl.module_san_pham.kieutui;

import com.example.fashionmanager.dto.sanpham.quanlykieutui.Response.KieuTuiDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.Response.KieuTuiResponse;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.CreateKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.ListKieuTuiRequest;
import com.example.fashionmanager.dto.sanpham.quanlykieutui.request.UpdateKieuTuiRequest;
import com.example.fashionmanager.entity.KieuTuiEntity;
import com.example.fashionmanager.service.CRUDService;
public interface KieuTuiService extends CRUDService<KieuTuiEntity, CreateKieuTuiRequest,
         UpdateKieuTuiRequest,ListKieuTuiRequest, KieuTuiResponse, KieuTuiDetailResponse> {
}
