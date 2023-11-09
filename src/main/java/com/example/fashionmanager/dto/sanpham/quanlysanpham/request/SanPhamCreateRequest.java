package com.example.fashionmanager.dto.sanpham.quanlysanpham.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamCreateRequest {
    private String maSanPham;

    private String tenSanPham;

    private String moTaChiTiet;

    private Long chatLieuId;

    private Long cauTrucKhuyId;

    private Long hoaTietId;

    private Long kieuDangId;

    private Long kieuDetId;

    private Long kieuTuiId;
    private Long lopLotId;
    private Long veAoId;

    private Long xeTaId;
}
