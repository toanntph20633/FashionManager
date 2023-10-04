package com.example.fashionmanager.dto.supplier_manager.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierResponse {
    private Long id;

    private String suppilerCode;

    private String suppilerName;

    private Boolean active;
}
