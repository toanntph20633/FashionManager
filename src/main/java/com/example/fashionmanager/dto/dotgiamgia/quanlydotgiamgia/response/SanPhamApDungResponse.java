package com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.response;

import com.example.fashionmanager.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamApDungResponse {
    private SanPhamResponse sanPhamResponse;

    private Long idDotGiamGia;

    private DiscountType loaiUuDai;

    private BigDecimal giaTriDuocGiam;
}
