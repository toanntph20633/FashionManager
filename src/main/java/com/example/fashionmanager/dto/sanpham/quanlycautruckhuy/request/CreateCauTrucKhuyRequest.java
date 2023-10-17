package com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Setter
@Getter
@SuperBuilder
public class CreateCauTrucKhuyRequest {
    private String tenCauTrucKhuy;
    private String moTa;
}
