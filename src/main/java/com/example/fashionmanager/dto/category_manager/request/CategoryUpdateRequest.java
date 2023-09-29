package com.example.fashionmanager.dto.category_manager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequest extends CategoryCreateRequest{
    @NotNull(message = "Không để trống id")
    private Long id;
    private Long categoryEntities;
    private  boolean active;

}
