package com.example.fashionmanager.dto.sanpham.quanlykieutui.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class UpdateKieuTuiRequest extends CreateKieuTuiRequest{
    private Long id;
}
