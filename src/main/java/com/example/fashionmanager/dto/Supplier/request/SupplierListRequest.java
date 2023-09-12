package com.example.fashionmanager.dto.Supplier.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SupplierListRequest {

    private int page;

    private int size;

    private String code;

    private String name;

    private boolean active = true;

}
