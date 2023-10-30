package com.example.fashionmanager.dto.sanpham.quanlykichthuoc.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class KichThuocDetailResponse {
    private Long id;
    private String maKichThuoc;
    private String tenKichThuoc;
    private Integer coTay;
    private Integer vongCo;
    private Integer vongMong;
    private Integer nach;
    private Integer soDoBapTay;
    private Integer soDaiCanhTay;
    private Integer doDaiVai;
    private Integer vongEo;
    private Integer vongNguc;
    private Integer doDaiAo;
}
