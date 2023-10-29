package com.example.fashionmanager.dto.sanpham.quanlychatlieu.request;

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
public class CreateChatLieuRequest {
    private String tenChatLieu;
    private String moTa;
}
