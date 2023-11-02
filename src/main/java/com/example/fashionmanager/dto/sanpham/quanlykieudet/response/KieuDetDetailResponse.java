package com.example.fashionmanager.dto.sanpham.quanlykieudet.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KieuDetDetailResponse {
    private Long id;
    private String tenKieuDet;
    private  String moTa;
}
