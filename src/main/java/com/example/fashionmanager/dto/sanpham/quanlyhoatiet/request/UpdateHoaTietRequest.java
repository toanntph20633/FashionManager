package com.example.fashionmanager.dto.sanpham.quanlyhoatiet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UpdateHoaTietRequest extends CreateHoaTietRequest{
    private Long id;
}
