package com.example.fashionmanager.service.impl.module_san_pham.loplot;

import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.CreateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.ListLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.request.UpdateLopLotRequest;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotDetailResponse;
import com.example.fashionmanager.dto.sanpham.quanlyloplot.response.LopLotResponse;
import com.example.fashionmanager.entity.LopLotEntity;
import com.example.fashionmanager.service.CRUDService;

public interface LopLotService extends CRUDService<LopLotEntity, CreateLopLotRequest
        , UpdateLopLotRequest, ListLopLotRequest, LopLotResponse, LopLotDetailResponse> {
}
