package com.example.fashionmanager.mapping.product_type_manager;

import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeCreateRequest;
import com.example.fashionmanager.dto.product_type_manager.request.ProductTypeUpdateRequest;
import com.example.fashionmanager.dto.product_type_manager.response.ProductTypeRespones;
import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {
     LoaiSanPhamEntity getProductTypeEntityCreate(ProductTypeCreateRequest productTypeCreateRequest);

     LoaiSanPhamEntity getProductTypeUpdateRequest(ProductTypeUpdateRequest productTypeUpdateRequest);

     ProductTypeRespones getProductTypeRespones(LoaiSanPhamEntity loaiSanPhamEntity);
}
