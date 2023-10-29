package com.example.fashionmanager.dto.sanpham.quanlykieudet.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KieuDetResponse {
    private Long id;
    private String tenKieuDet;
    private  String moTa;
}
