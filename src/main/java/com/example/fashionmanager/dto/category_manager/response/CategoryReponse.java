package com.example.fashionmanager.dto.category_manager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryReponse {
    private Long id;

    private Long categoryEntities;

    private String categoryCode;

    private String categoryName;

    private Boolean active;
}
