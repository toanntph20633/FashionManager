package com.example.fashionmanager.dto.color_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorCreateRequest {
    @NotNull(message = "Không để trống colorCode")
    @NotBlank(message = "Không để trống colorCode")
    private String colorCode;
    @NotNull(message = "Không để trống colorName")
    @NotBlank(message = "Không để trống colorName")
    private String colorName;
}
