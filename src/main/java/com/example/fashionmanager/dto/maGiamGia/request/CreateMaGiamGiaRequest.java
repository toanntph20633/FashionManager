package com.example.fashionmanager.dto.maGiamGia.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
public class CreateMaGiamGiaRequest {
    private String maVoucher;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String mota;
    private BigDecimal soTienYeuCau;
    private String hinhThuckhuyenmai;
    private String hinhThucApDung;
    private BigDecimal giaTriDuocGiam;
    private  Integer soLuongMaGianGia;
}
