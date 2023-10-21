package com.example.fashionmanager.dto.sanpham.quanlyloplot.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListLopLotRequest extends ListRequestDto {
    private String tenLopLot;
}
