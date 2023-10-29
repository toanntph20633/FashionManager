package com.example.fashionmanager.dto.sanpham.quanlycautruckhuy.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateCauTrucKhuyRequest extends CreateCauTrucKhuyRequest{
    private Long id;
}
