package com.example.fashionmanager.dto.sanpham.quanlychatlieu.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateChatLieuRequest extends CreateChatLieuRequest{
    private Long id;
}
