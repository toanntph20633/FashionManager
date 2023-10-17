package com.example.fashionmanager.service;

import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaDetailReponse;
import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaReponse;
import com.example.fashionmanager.dto.MaGiamGia.Request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.entity.MaGiamGiaEntity;

public interface PhieuGiamGiaService extends CRUDService<MaGiamGiaEntity, CreateMaGiamGiaRequest,UpdateMaGiamGiaRequest, ListMaGiamGiaRequest
        ,MaGiamGiaDetailReponse, MaGiamGiaReponse>{
}
