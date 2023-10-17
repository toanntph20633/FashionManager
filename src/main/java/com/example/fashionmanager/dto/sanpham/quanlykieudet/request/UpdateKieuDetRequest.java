package com.example.fashionmanager.dto.sanpham.quanlykieudet.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateKieuDetRequest extends CreateKieuDetRequest {
    private Long id;
}
