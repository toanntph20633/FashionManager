package com.example.fashionmanager.dto.supplier_manager.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierResponse {
    private Long id;

    private String supplierCode;

    private String supplierName;
}
