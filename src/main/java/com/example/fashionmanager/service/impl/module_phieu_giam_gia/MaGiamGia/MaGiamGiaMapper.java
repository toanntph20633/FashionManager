package com.example.fashionmanager.service.impl.module_phieu_giam_gia.MaGiamGia;

import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaReponse;
import com.example.fashionmanager.dto.MaGiamGia.Request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.entity.MaGiamGiaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaGiamGiaMapper {
        MaGiamGiaEntity getMaGiamGiaCreate(CreateMaGiamGiaRequest createMaGiamGiaRequest);
        MaGiamGiaEntity getMaGiamGiaUpdate(UpdateMaGiamGiaRequest updateMaGiamGiaRequest);
        MaGiamGiaReponse getMaGiamGiaReponse(MaGiamGiaEntity maGiamGiaEntity);
}
