package com.example.fashionmanager.dto.sanpham.quanlyxeta.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CreateXeTaRequest {
    private String tenXeTa;
    private String moTa;
}
