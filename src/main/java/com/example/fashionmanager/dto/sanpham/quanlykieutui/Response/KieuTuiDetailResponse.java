package com.example.fashionmanager.dto.sanpham.quanlykieutui.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class KieuTuiDetailResponse {
    private Long id;
    private String tenKieuTui;
    private String moTa;
}
