package com.example.fashionmanager.dto.sanpham.quanlydanhmuc.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DanhMucResponse {
    private Long id;
    private String maDanhMuc;
    private String tenDanhMuc;
}
