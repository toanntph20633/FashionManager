package com.example.fashionmanager.dto.sanpham.quanlyveao.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class VeAoResponse {
    private String tenVeAo;
    private String moTa;
}
