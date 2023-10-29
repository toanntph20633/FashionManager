package com.example.fashionmanager.dto.sanpham.quanlyxeta.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class XeTaDetailResponse {
    private Long id;
    private String tenXeTa;
    private String moTa;
}
