package com.example.fashionmanager.dto.color_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorUpdateRequest extends ColorCreateRequest{
    @NotNull(message = "Không để trống id")
    private Long id;
    private boolean active;
}
