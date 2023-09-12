package com.example.fashionmanager.dto.Supplier.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuppliearCreatRequest {
    @NotNull(message = "Không để trống supplierCode")
    @NotBlank(message = "Không để trống supplierCode")
    private String supplierCode;
    @NotNull(message = "Không để trống supplierName")
    @NotBlank(message = "Không để trống supplierName")
    private String supplierName;

}
