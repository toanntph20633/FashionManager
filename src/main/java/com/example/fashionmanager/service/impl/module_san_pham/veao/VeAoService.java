package com.example.fashionmanager.service.impl.module_san_pham.veao;

import com.example.fashionmanager.dto.sanpham.quanlyveao.request.CreateVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.ListVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.request.UpdateVeAoRequest;
import com.example.fashionmanager.dto.sanpham.quanlyveao.response.VeAoDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyveao.response.VeAoResponse;
import com.example.fashionmanager.entity.VeAoEntity;
import com.example.fashionmanager.service.CRUDService;

public interface VeAoService extends CRUDService<VeAoEntity, CreateVeAoRequest, UpdateVeAoRequest,
        ListVeAoRequest, VeAoResponse, VeAoDetailResponse> {
}
