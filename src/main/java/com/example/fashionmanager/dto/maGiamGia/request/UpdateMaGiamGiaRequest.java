package com.example.fashionmanager.dto.maGiamGia.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateMaGiamGiaRequest extends CreateMaGiamGiaRequest {
        private Long id;
}
