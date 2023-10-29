package com.example.fashionmanager.dto.sanpham.quanlyveao.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ListVeAoRequest extends ListRequestDto {
    private String tenVeAo;
}
