package com.example.fashionmanager.dto.product_type_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeUpdateRequest extends ProductTypeCreateRequest{

    @NotNull(message = "Không để trống id")
    private Long id;

    private Boolean active;
}
