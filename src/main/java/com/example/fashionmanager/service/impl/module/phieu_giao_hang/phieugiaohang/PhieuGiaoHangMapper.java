package com.example.fashionmanager.service.impl.module.phieu_giao_hang.phieugiaohang;

import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.request.PhieuGiaoHangUpdateRequest;
import com.example.fashionmanager.dto.phieugiaohang.quanliphieugiaohang.respones.PhieuGiaoHangRespones;
import com.example.fashionmanager.entity.PhieuGiaoHangEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhieuGiaoHangMapper {
    PhieuGiaoHangEntity getPhieuGiaoHangEntity(PhieuGiaoHangUpdateRequest phieuGiaoHangUpdateRequest);
    PhieuGiaoHangRespones getPhieuGiaoHangRespones(PhieuGiaoHangEntity phieuGiaoHangEntity);
}
