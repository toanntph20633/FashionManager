package com.example.fashionmanager.dto.ProductType.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PTypeUpdateRequest extends PTypeCreateRequest{
    @NotNull(message = "Không để trống id")
    private Long id;
}
