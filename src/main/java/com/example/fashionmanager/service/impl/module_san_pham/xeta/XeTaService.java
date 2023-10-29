package com.example.fashionmanager.service.impl.module_san_pham.xeta;

import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.CreateXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.ListXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.request.UpdateXeTaRequest;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.response.XeTaDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyxeta.response.XeTaResponse;
import com.example.fashionmanager.entity.XetaEntity;
import com.example.fashionmanager.service.CRUDService;

public interface XeTaService extends CRUDService<XetaEntity, CreateXeTaRequest, UpdateXeTaRequest,
        ListXeTaRequest, XeTaResponse, XeTaDetailResponse> {
}
