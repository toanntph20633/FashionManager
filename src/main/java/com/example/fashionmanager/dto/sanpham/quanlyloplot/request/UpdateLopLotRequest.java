package com.example.fashionmanager.dto.sanpham.quanlyloplot.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateLopLotRequest extends CreateLopLotRequest{
    private Long id;
}
