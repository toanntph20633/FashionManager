package com.example.fashionmanager.dto.ProductType.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PTypeResponse {
    private Long id;

    private String ProductTypeCode;

    private String ProductTypeName;
}

