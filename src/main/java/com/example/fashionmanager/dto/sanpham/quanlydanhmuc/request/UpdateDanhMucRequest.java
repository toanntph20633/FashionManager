package com.example.fashionmanager.dto.sanpham.quanlydanhmuc.request;

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
public class UpdateDanhMucRequest extends CreateDanhMucRequest{
    private Long id;
}
