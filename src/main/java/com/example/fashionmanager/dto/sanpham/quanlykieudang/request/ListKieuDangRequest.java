package com.example.fashionmanager.dto.sanpham.quanlykieudang.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListKieuDangRequest extends ListRequestDto {
    private String tenKieuDang;

}
