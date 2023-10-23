package com.example.fashionmanager.dto.sanpham.quanlyloplot.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LopLotDetailResponse {
    private Long id;
    private String tenLopLot;
    private String moTa;
}
