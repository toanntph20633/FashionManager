package com.example.fashionmanager.dto.sanpham.quanlychatlieu.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class CreateChatLieuRequest {
    private String tenChatLieu;
    private String moTa;
}
