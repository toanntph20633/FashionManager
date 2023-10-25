package com.example.fashionmanager.dto.maGiamGia.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaGiamGiaReponse {
    private Long id;
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
