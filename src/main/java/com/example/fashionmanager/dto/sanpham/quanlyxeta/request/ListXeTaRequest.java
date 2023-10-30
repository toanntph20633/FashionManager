package com.example.fashionmanager.dto.sanpham.quanlyxeta.request;

import com.example.fashionmanager.dto.ListRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ListXeTaRequest extends ListRequestDto {
    private String tenXeTa;
}
