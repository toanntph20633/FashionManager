package com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class DotGiamGiaReponse {
    private String tenDotgiamGia;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;
}
