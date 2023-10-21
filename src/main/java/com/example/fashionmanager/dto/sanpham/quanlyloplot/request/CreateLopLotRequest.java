package com.example.fashionmanager.dto.sanpham.quanlyloplot.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateLopLotRequest {
    private String tenLopLot;
    private String mota;

}
