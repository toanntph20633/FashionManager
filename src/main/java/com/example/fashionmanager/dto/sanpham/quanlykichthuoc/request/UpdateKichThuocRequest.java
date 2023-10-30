package com.example.fashionmanager.dto.sanpham.quanlykichthuoc.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKichThuocRequest extends CreateKichThuocRequest{
    private Long id;
}
