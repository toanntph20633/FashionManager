package com.example.fashionmanager.dto.sanpham.quanlykieudet.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateKieuDetRequest extends CreateKieuDetRequest {
    private Long id;
}
