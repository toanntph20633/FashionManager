package com.example.fashionmanager.dto.MaGiamGia.Reponse;

import com.example.fashionmanager.entity.HangEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaGiamGiaReponse {
    Long id;
    String maVoucher;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    String mota;
    BigDecimal soTienYeuCau;
    String hinhThuckhuyenMai;
    String hinhThucApDung;
    Integer giaTriDuocGiam;
    Integer soLuongMaGianGia;
    private Boolean active = true;
//    HangEntity hangEntity;
}
