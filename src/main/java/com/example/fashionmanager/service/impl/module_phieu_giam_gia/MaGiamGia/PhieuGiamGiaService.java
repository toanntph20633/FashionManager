package com.example.fashionmanager.service.impl.module_phieu_giam_gia.MaGiamGia;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaDetailReponse;
import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaReponse;
import com.example.fashionmanager.dto.MaGiamGia.Request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.entity.MaGiamGiaEntity;
import com.example.fashionmanager.service.CRUDService;

public interface PhieuGiamGiaService  {
    ListReponseDto<MaGiamGiaReponse> getList(ListMaGiamGiaRequest request);

    ResponseDto<MaGiamGiaReponse> save(CreateMaGiamGiaRequest request);

    ResponseDto<MaGiamGiaReponse> update(UpdateMaGiamGiaRequest request);

    ResponseDto<MaGiamGiaReponse> delete(Long id);

    ResponseDto<MaGiamGiaReponse> detail(Long id);
    ResponseDto<MaGiamGiaReponse> changeActive(Long id);
}
