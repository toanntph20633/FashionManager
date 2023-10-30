package com.example.fashionmanager.dto.sanpham.quanlykieudang.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateKieuDangRequest extends CreateKieuDangRequest {
    private Long id;
}
