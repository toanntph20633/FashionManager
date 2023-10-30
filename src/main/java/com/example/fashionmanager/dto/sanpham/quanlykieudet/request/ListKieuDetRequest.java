package com.example.fashionmanager.dto.sanpham.quanlykieudet.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListKieuDetRequest extends ListRequestDto {
    private String tenKieuDet;
}
