package com.example.fashionmanager.service.impl.module_khach_hang.hang;

import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.responst.HangResponse;
import com.example.fashionmanager.entity.HangEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HangMapper {
//    HangEntity getHangEntity1(HangCreateResquest createResquest);
//    HangEntity getHangEntity1(HangUpdateRequest updateRequest);
//    HangResponse getHangReponse1(HangEntity hangEntity);

    HangEntity getHangEntity(HangCreateResquest createResquest);
    HangEntity getHangEntity(HangUpdateRequest updateRequest);
    HangResponse getHangReponse(HangEntity hangEntity);
}
