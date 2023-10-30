package com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CauTrucKhuyResponse {
    private Long id;
    private String tenCauTrucKhuy;
    private String moTa;
}
