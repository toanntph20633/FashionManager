package com.example.fashionmanager.dto.khachhang.quanlykhachhang.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class KhachHangUpdateRequest extends KhachHangCreateResquest{

    @NotNull(message = "Không để trống id")
    private Long id;
}
