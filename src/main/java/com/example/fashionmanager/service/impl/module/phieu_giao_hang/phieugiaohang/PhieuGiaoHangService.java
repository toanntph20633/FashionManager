package com.example.fashionmanager.service.impl.module.phieu_giao_hang.phieugiaohang;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangCreateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangListRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangUpdateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.respones.PhieuGiaoHangRespones;

public interface PhieuGiaoHangService {
    ListReponseDto<PhieuGiaoHangRespones> getAll(
                                                 PhieuGiaoHangListRequest phieuGiaoHangListRequest);

    ResponseDto<PhieuGiaoHangRespones> save(PhieuGiaoHangCreateRequest request);

    ResponseDto<PhieuGiaoHangRespones> update(PhieuGiaoHangUpdateRequest request);

    ResponseDto<PhieuGiaoHangRespones> delete(Long id);

    ResponseDto<PhieuGiaoHangRespones> detail(Long id);

    ResponseDto<PhieuGiaoHangRespones> changActive(Long id);


}
