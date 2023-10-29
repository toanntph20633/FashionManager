package com.example.fashionmanager.dto.sanpham.quanlyxeta.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateXeTaRequest extends CreateXeTaRequest{
    private Long id;
}
