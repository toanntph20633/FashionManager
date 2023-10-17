package com.example.fashionmanager.dto.MaGiamGia.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UpdateMaGiamGiaRequest extends CreateMaGiamGiaRequest {
    Long id;
}
