package com.example.fashionmanager.dto.product_type_manager.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductTypeListRequest {

    private int page;

    private int size;

    private String code;

    private String name;

    private Boolean active = true;
}
