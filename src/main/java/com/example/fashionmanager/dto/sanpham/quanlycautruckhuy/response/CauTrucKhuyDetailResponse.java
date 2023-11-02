package com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CauTrucKhuyDetailResponse {
    private Long id;
    private String tenCauTrucKhuy;
    private  String moTa;
}
