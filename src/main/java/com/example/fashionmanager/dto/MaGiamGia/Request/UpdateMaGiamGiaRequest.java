package com.example.fashionmanager.dto.MaGiamGia.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter

public class UpdateMaGiamGiaRequest extends CreateMaGiamGiaRequest {
    @NotNull(message = "Không để trống id")
    Long id;
}
