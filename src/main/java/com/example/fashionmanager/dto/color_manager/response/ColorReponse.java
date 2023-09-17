package com.example.fashionmanager.dto.color_manager.response;

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
public class ColorReponse {
    private Long id;

    private String colorCode;

    private String colorName;

    private Boolean active;
}
