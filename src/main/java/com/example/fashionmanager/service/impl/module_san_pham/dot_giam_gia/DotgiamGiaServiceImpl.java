package com.example.fashionmanager.service.impl.module_san_pham.dot_giam_gia;

import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaCreateRequest;
import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaListRequest;
import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request.DotGiamGiaUpdateRequest;
import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.request.SanPhamApDungDGGRequest;
import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.response.DotGiamGiaReponse;
import com.example.fashionmanager.dto.dotgiamgia.quanlydotgiamgia.response.DotGiamGiaResponseDetail;
import com.example.fashionmanager.entity.DotGiamGiaEntity;
import com.example.fashionmanager.entity.SanPhamApDungDGGEntity;
import com.example.fashionmanager.entity.SanPhamEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.DotGiamGiaRepository;
import com.example.fashionmanager.repository.SanPhamApDungDDGRepository;
import com.example.fashionmanager.repository.SanPhamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DotgiamGiaServiceImpl implements DotGiamGiaService {
    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;
    @Autowired
    private SanPhamApDungDDGRepository sanPhamApDungDDGRepository;
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public ResponseEntity<?> getList(DotGiamGiaListRequest dotGiamGiaListRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> create(DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        DotGiamGiaEntity dotGiamGiaEntity = new DotGiamGiaEntity();
        dotGiamGiaEntity.setTenDotGiamGia(dotGiamGiaCreateRequest.getTenDotGiamGia());
        if (dotGiamGiaCreateRequest.getNgayBatDau().isAfter(dotGiamGiaCreateRequest.getNgayKetThuc())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.BAD_REQUEST, "Ngày bắt đầu không được sau ngày kết thúc"
                    )
            );
        }
        dotGiamGiaEntity.setNgayBatDau(dotGiamGiaCreateRequest.getNgayBatDau());
        dotGiamGiaEntity.setNgayKetThuc(dotGiamGiaCreateRequest.getNgayKetThuc());
        dotGiamGiaEntity.setLoaiUuDaiDDG(dotGiamGiaCreateRequest.getLoaiUuDaiDDG());
        switch (dotGiamGiaCreateRequest.getLoaiUuDaiDDG()) {
            case DANH_MUC -> {
                dotGiamGiaEntity = loaiUuDaiDanhMuc(dotGiamGiaEntity, dotGiamGiaCreateRequest);
                break;
            }
            case HOA_DON -> {
                dotGiamGiaEntity = loaiUuDaiHoaDon(dotGiamGiaEntity, dotGiamGiaCreateRequest);
                break;
            }
            case SAN_PHAM -> {
                dotGiamGiaEntity = loaiUuDaiSanPham(dotGiamGiaEntity, dotGiamGiaCreateRequest);
                break;
            }
        }
        dotGiamGiaEntity.setDotGiamGiaStatus(dotGiamGiaCreateRequest.getDotGiamGiaStatus());
        dotGiamGiaRepository.save(dotGiamGiaEntity);
        return ResponseEntity.ok("CREATED");
    }

    private DotGiamGiaEntity loaiUuDaiDanhMuc(DotGiamGiaEntity dotGiamGiaEntity, DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        return dotGiamGiaEntity;
    }

    private DotGiamGiaEntity loaiUuDaiHoaDon(DotGiamGiaEntity dotGiamGiaEntity, DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        dotGiamGiaEntity.setSoTienHoaDonYeuCau(dotGiamGiaCreateRequest.getSoTienHoaDonYeuCau());
        dotGiamGiaEntity.setLoaiGiamGiaHD(dotGiamGiaCreateRequest.getLoaiGiamGiaHD());
        switch (dotGiamGiaCreateRequest.getLoaiGiamGiaHD()) {
            case AMOUNT -> {
                dotGiamGiaEntity.setGiaTriGiamHD(dotGiamGiaCreateRequest.getGiaTriGiamHD());
                break;
            }
            case PERCENT -> {
                int giaTriGiam = Integer.parseInt(dotGiamGiaCreateRequest.getGiaTriGiamHD().toString());
                if (giaTriGiam > 100 || giaTriGiam < 0) {
                    throw new FashionManagerException(
                            new ErrorResponse(
                                    HttpStatus.BAD_REQUEST, "Giá trị giảm phải trong khoảng từ 0% -> 100%"
                            )
                    );
                }
                dotGiamGiaEntity.setGiaTriGiamHD(dotGiamGiaCreateRequest.getGiaTriGiamHD());
                break;
            }
            case NONE -> {
                break;
            }
        }
        return dotGiamGiaEntity;
    }

    private DotGiamGiaEntity loaiUuDaiSanPham(DotGiamGiaEntity dotGiamGiaEntity, DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        if (dotGiamGiaCreateRequest.getSanPhamApDungDGGs() != null && !dotGiamGiaCreateRequest.getSanPhamApDungDGGs().isEmpty()) {
            Set<SanPhamApDungDGGEntity> sanPhamApDungDGGEntities = dotGiamGiaCreateRequest.getSanPhamApDungDGGs().stream().map(x -> {
                SanPhamApDungDGGEntity sanPhamApDungDGGEntity = new SanPhamApDungDGGEntity();
                SanPhamEntity sanPhamEntity = sanPhamRepository.findById(x.getIdSanPham()).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND, "không tìm thấy id sản phẩm có id = " + x.getIdSanPham()
                        )
                ));
                sanPhamApDungDGGEntity.setSanPhamEntity(sanPhamEntity);
                sanPhamApDungDGGEntity.setLoaiUuDai(x.getLoaiGiamGia());
                switch (x.getLoaiGiamGia()) {
                    case AMOUNT -> {
                        sanPhamApDungDGGEntity.setGiaTriDuocGiam(x.getGiaTriDuocGiam());
                        break;
                    }
                    case PERCENT -> {
                        int giaTriGiam = Integer.parseInt(x.getGiaTriDuocGiam().toString());
                        if (giaTriGiam > 100 || giaTriGiam < 0) {
                            throw new FashionManagerException(
                                    new ErrorResponse(
                                            HttpStatus.BAD_REQUEST, "Giá trị giảm phải trong khoảng từ 0% -> 100%"
                                    )
                            );
                        }
                        sanPhamApDungDGGEntity.setGiaTriDuocGiam(x.getGiaTriDuocGiam());
                        break;
                    }
                    case NONE -> {
                        break;
                    }
                }
                return sanPhamApDungDGGEntity;
            }).collect(Collectors.toSet());
            dotGiamGiaEntity.setSanPhamApDungDGGEntities(sanPhamApDungDGGEntities);
        }
        return dotGiamGiaEntity;
    }

    @Override
    public ResponseEntity<?> update(DotGiamGiaUpdateRequest dotGiamGiaUpdateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public DotGiamGiaEntity mappingByCreateRequest(DotGiamGiaCreateRequest dotGiamGiaCreateRequest) {
        return null;
    }

    @Override
    public DotGiamGiaEntity mappingByUpdateRequest(DotGiamGiaUpdateRequest dotGiamGiaUpdateRequest) {
        return null;
    }

    @Override
    public DotGiamGiaReponse mappingByResponse(DotGiamGiaEntity dotGiamGiaEntity) {
        return null;
    }

    @Override
    public DotGiamGiaResponseDetail mappingResponseDetail(DotGiamGiaEntity dotGiamGiaEntity) {
        return null;
    }
}
