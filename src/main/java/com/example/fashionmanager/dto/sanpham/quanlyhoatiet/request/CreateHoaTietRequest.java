package com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateHoaTietRequest {
    private String tenHoaTiet;
    private String moTa;
}
