package com.example.fashionmanager.service.impl.module_Ma_Giam_Gia.maGiamGia;

import com.example.fashionmanager.dto.maGiamGia.request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.response.MaGiamGiaDetailReponse;
import com.example.fashionmanager.dto.maGiamGia.response.MaGiamGiaReponse;
import com.example.fashionmanager.entity.MaGiamGiaEntity;
import com.example.fashionmanager.service.CRUDService;

public interface MaGiamGiaService extends CRUDService<MaGiamGiaEntity, CreateMaGiamGiaRequest, UpdateMaGiamGiaRequest, ListMaGiamGiaRequest
        , MaGiamGiaReponse, MaGiamGiaDetailReponse> {
}
