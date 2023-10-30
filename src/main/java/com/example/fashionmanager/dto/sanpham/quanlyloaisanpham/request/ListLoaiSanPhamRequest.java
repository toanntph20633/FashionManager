package com.example.fashionmanager.dto.sanpham.quanlyloaisanpham.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListLoaiSanPhamRequest extends ListRequestDto {
    private String tenLoai;
}
