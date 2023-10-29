package com.example.fashionmanager.service.impl.module_san_pham.hoatiet;

import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.CreateHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.ListHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request.UpdateHoaTietRequest;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.response.HoaTietResponse;
import com.example.fashionmanager.dto.sanpham.quanlyhoatiet.response.HoaTietDetailResponse;
import com.example.fashionmanager.entity.HoaTietEntity;
import com.example.fashionmanager.service.CRUDService;

public interface HoaTietService extends CRUDService<HoaTietEntity,CreateHoaTietRequest, UpdateHoaTietRequest,
        ListHoaTietRequest, HoaTietResponse, HoaTietDetailResponse> {
}
