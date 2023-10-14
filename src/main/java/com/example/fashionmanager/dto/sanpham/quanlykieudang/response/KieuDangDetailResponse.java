package com.example.fashionmanager.dto.sanpham.quanlykieudang.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KieuDangDetailResponse {
    private Long id;
    private String tenKieuDang;
    private  String moTa;
}
