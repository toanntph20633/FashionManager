package com.example.fashionmanager.dto.supplier_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierUpdateRequest extends SupplierCreateRequest {
    @NotNull(message = "Không để trống id")

    private Long id;

    private Boolean activate;

}
