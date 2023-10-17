package com.example.fashionmanager.dto.MaGiamGia.Request;

import com.example.fashionmanager.entity.HangEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
public class CreateMaGiamGiaRequest {
    String maVoucher;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    String mota;
    BigDecimal soTienYeuCau;
    String hinhThuckhuyenmai;
    String hinhThucApDung;
    int giaTriDuocGiam;
    int soLuongMaGianGia;
//    HangEntity hangEntity;
}
