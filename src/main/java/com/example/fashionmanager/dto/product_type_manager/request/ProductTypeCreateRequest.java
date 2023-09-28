package com.example.fashionmanager.dto.product_type_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeCreateRequest {

    @NotNull(message = "Không để trống productTypeCode")
    @NotBlank(message = "Không để trống productTypeCode")
    private String productTypeCode;

    @NotNull(message = "Không để trống productTypeName")
    @NotBlank(message = "Không để trống productTypeName")
    private String productTypeName;
}
