package com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListCauTrucKhuyRequest extends ListRequestDto {
    private String tenCauTrucKhuy;
}
