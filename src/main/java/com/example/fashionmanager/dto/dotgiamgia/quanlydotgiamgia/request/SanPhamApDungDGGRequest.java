package com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request;

import com.example.fashionmanager.enums.DiscountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SanPhamApDungDGGRequest {
    private Long idSanPham;
    private DiscountType loaiGiamGia;
    private BigDecimal giaTriDuocGiam;
}
