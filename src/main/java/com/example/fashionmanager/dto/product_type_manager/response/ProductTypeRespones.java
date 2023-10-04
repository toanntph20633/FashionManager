package com.example.fashionmanager.dto.product_type_manager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductTypeRespones {

    private Long id;

    private String productTypeCode;

    private String productTypeName;

    private Boolean active;
}
