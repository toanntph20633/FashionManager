package com.example.fashionmanager.dto.Supplier.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierUpdateRequest extends SuppliearCreatRequest{
    @NotNull(message = "Không để trống id")
    private Long id;

}
