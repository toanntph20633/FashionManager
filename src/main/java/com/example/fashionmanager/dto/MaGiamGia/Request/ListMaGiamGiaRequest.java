package com.example.fashionmanager.dto.MaGiamGia.Request;

import com.example.fashionmanager.dto.ListRequestDto;
import com.example.fashionmanager.entity.HangEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
public class ListMaGiamGiaRequest extends ListRequestDto {
    String maVoucher;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    String mota;
    BigDecimal soTienYeuCau;
    String hinhThuckhuyenmai;
    String hinhThucApDung;
    Integer giaTriDuocGiam;
    Integer soLuongMaGianGia;
    private Boolean active = true;
//    HangEntity hangEntity;
}
