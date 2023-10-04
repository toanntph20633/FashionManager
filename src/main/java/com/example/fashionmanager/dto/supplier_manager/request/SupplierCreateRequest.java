package com.example.fashionmanager.dto.supplier_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierCreateRequest {
    @NotNull(message = "Không để trống supplierCode")
    @NotBlank(message = "Không để trống supplierCode")
    private String suppilerCode;
    @NotNull(message = "Không để trống supplierName")
    @NotBlank(message = "Không để trống supplierName")
    private String suppilerName;

}
