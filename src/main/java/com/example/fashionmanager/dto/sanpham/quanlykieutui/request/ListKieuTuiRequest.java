package com.example.fashionmanager.dto.sanpham.quanlykieutui.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListKieuTuiRequest extends ListRequestDto {
    private String tenKieuTui;
}
