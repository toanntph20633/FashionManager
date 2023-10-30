package com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.response;

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
public class LoaiSanPhamDetailResponse {
    private Long id;
    private String maLoai;
    private String tenLoai;
}
