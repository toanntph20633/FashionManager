package com.example.fashionmanager.dto.sanpham.quanlyhoatiet.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HoaTietResponse {
    private Long id;
    private String tenHoaTiet;
    private  String moTa;
}
