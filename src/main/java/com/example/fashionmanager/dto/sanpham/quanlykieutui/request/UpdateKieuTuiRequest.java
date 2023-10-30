package com.example.fashionmanager.dto.sanpham.quanlykieutui.request;

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
public class UpdateKieuTuiRequest extends CreateKieuTuiRequest{
    private Long id;
}
