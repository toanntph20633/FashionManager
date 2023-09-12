package com.example.fashionmanager.dto.ProductType.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PTypeCreateRequest {
    @NotNull(message = "Không để trống ProductTypeCode")
    @NotBlank(message = "Không để trống ProductTypeCode")
    private String ProductTypeCode;

    @NotNull(message = "Không để trống ProductTypeName")
    @NotBlank(message = "Không để trống ProductTypeName")
    private String ProductTypeName;
}
