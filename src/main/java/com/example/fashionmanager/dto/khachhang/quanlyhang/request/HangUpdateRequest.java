package com.example.fashionmanager.dto.khachhang.quanlyhang.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HangUpdateRequest extends HangCreateResquest {
    @NotNull(message = "Không để trống id")
    private Long id;
}
