package com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListDanhMucRequest extends ListRequestDto {
    private String maDanhMuc;
    private String tenDanhMuc;
}
