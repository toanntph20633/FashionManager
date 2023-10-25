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
public class UpdateLopLotRequest extends CreateLopLotRequest{
    private Long id;
}
