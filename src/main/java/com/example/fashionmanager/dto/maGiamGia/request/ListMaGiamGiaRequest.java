package com.example.fashionmanager.dto.maGiamGia.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
public class ListMaGiamGiaRequest  extends ListRequestDto {
    private String maVoucher;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private BigDecimal giaTriDuocGiam;
    private  Integer soLuongMaGianGia;
}
