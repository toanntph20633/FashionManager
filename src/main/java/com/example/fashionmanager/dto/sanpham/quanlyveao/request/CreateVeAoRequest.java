package com.example.fashionmanager.dto.sanpham.quanlyveao.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateVeAoRequest {
    private String tenVeAo;
    private String moTa;
}
