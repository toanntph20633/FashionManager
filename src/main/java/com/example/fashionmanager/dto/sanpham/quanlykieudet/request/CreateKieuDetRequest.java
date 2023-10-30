package com.example.fashionmanager.dto.sanpham.quanlykieudet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateKieuDetRequest {
    private String tenKieuDet;
    private String moTa;
}
