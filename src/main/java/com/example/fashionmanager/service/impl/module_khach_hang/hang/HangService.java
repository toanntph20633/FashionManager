package com.example.fashionmanager.service.impl.module_khach_hang.hang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangCreateResquest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangListRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.request.HangUpdateRequest;
import com.example.fashionmanager.dto.khachhang.quanlyhang.responst.HangResponse;
import com.example.fashionmanager.service.CRUDService;

public interface HangService  {
    ListReponseDto<HangResponse> getList(HangListRequest request);
    ResponseDto<HangResponse> getByID(Long id);
    ResponseDto<HangResponse> create(HangCreateResquest request);
    ResponseDto<HangResponse> update(HangUpdateRequest request);
    ResponseDto<HangResponse> delete(Long id);
    ResponseDto<HangResponse> changeActive(Long id);
}
