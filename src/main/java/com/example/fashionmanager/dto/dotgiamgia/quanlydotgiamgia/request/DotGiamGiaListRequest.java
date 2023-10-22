package com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request;

import com.example.fashionmanager.enums.DotGiamGiaStatus;
import com.example.fashionmanager.enums.LoaiUuDaiDDG;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Builder
public class DotGiamGiaListRequest {
    private String tenDotGiamGia;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetthuc;

    private LoaiUuDaiDDG loaiUuDaiDDG;

    private DotGiamGiaStatus dotGiamGiaStatus;
}
