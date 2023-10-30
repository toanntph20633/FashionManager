package com.example.fashionmanager.dto.sanpham.quanlyloplot.request;

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
public class CreateLopLotRequest {
    private String tenLopLot;
    private String mota;

}
