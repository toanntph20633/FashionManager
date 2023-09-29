package com.example.fashionmanager.dto.category_manager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {

    @NotNull(message = "Không để trống categoryCode")
    @NotBlank(message = "Không để trống categoryCode")
    private String categoryCode;
    @NotNull(message = "Không để trống categoryName")
    @NotBlank(message = "Không để trống categoryName")
    private String categoryName;


}
