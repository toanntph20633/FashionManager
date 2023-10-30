package com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class UpdateLoaiSanPhamRequest extends CreateLoaiSanPhamRequest{
    private Long id;
}
