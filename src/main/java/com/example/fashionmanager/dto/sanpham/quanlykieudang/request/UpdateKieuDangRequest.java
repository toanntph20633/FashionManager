package com.example.fashionmanager.dto.sanpham.quanlykieudang.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateKieuDangRequest extends CreateKieuDangRequest {
    private Long id;
}
