package com.example.fashionmanager.service.impl.module_Ma_Giam_Gia.maGiamGia;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.maGiamGia.request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.dto.maGiamGia.response.MaGiamGiaDetailReponse;
import com.example.fashionmanager.dto.maGiamGia.response.MaGiamGiaReponse;
import com.example.fashionmanager.dto.sanpham.quanlykieudang.response.KieuDangResponse;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.MaGiamGiaEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.MaGiamGiaRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaGiamGiaServiceImpl implements MaGiamGiaService {
    @Autowired
    MaGiamGiaRepository maGiamGiaRepository;
    @Override
    public ResponseEntity<?> getList(ListMaGiamGiaRequest listMaGiamGiaRequest) {
//        String maVoucher = listMaGiamGiaRequest.getMaVoucher();
//        int page = listMaGiamGiaRequest.getPage();
//        int size = listMaGiamGiaRequest.getSize();
//
//        PageRequest pageRequest = PageRequest.of(page - 1, size);
//        Page<MaGiamGiaEntity> result = maGiamGiaRepository.findByMaVoucher(maVoucher, pageRequest);
//        ListReponseDto<MaGiamGiaReponse> listReponseDto = new ListReponseDto<MaGiamGiaReponse>();
////        listReponseDto.setItems(listReponseDto);
//        listReponseDto.setHasNextPage(result.hasNext());
//        listReponseDto.setHasPreviousPage(result.hasPrevious());
//        listReponseDto.setPageCount(result.getTotalPages());
//        listReponseDto.setPageSize(result.getSize());
//        return ResponseEntity.ok(result);
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));

        Pageable pageable = PageRequest.of(listMaGiamGiaRequest.getPage(), listMaGiamGiaRequest.getSize(), sort);

        Specification<MaGiamGiaEntity> maGiamGiaEntitySpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(listMaGiamGiaRequest.getMaVoucher())) {
                predicates.add(criteriaBuilder.like(root.get("maVoucher"), "%" + listMaGiamGiaRequest.getMaVoucher() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
        Page<MaGiamGiaEntity> maGiamGiaEntities = maGiamGiaRepository.findAll(maGiamGiaEntitySpecification, pageable);
        List<MaGiamGiaReponse> maGiamGiaReponses = maGiamGiaEntities.stream().map(maGiamGiaEntity -> mappingByResponse(maGiamGiaEntity)).toList();
        ListReponseDto<MaGiamGiaReponse> listReponseDto = new ListReponseDto<MaGiamGiaReponse>();
        listReponseDto.setItems(maGiamGiaReponses);
        listReponseDto.setHasNextPage(maGiamGiaEntities.hasNext());
        listReponseDto.setHasPreviousPage(maGiamGiaEntities.hasPrevious());
        listReponseDto.setPageCount(maGiamGiaEntities.getTotalPages());
        listReponseDto.setPageSize(maGiamGiaEntities.getSize());

        return ResponseEntity.ok(listReponseDto);
    }
    @Override
    public ResponseEntity<?> getById(Long id) {
        MaGiamGiaEntity maGiamGiaEntity = maGiamGiaRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        return ResponseEntity.ok(mappingResponseDetail(maGiamGiaEntity));
    }

    @Override
    public ResponseEntity<?> create(CreateMaGiamGiaRequest createMaGiamGiaRequest) {
        MaGiamGiaEntity maGiamGiaEntity = mappingByCreateRequest(createMaGiamGiaRequest);
        maGiamGiaRepository.save(maGiamGiaEntity);
        return ResponseEntity.ok(mappingByResponse(maGiamGiaEntity));
    }

    @Override
    public ResponseEntity<?> update(UpdateMaGiamGiaRequest updateMaGiamGiaRequest) {
        if (!maGiamGiaRepository.existsById(updateMaGiamGiaRequest.getId())) {
            throw new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể"));
        }
        MaGiamGiaEntity entity = mappingByUpdateRequest(updateMaGiamGiaRequest);
        maGiamGiaRepository.save(entity);
        return ResponseEntity.ok(mappingByResponse(entity));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        MaGiamGiaEntity maGiamGiaEntity = maGiamGiaRepository.findById(id).orElseThrow(() -> new FashionManagerException(new ErrorResponse(HttpStatus.NOT_FOUND, "Không tìm thấy thực thể")));
        maGiamGiaEntity.setDeleted(true);
        maGiamGiaRepository.save(maGiamGiaEntity);
        return ResponseEntity.ok(mappingByResponse(maGiamGiaEntity));
    }

    @Override
    public ResponseEntity<?> changeActive(Long id) {
        return null;
    }

    @Override
    public MaGiamGiaEntity mappingByCreateRequest(CreateMaGiamGiaRequest createMaGiamGiaRequest) {
        return MaGiamGiaEntity.builder()
                .maVoucher(createMaGiamGiaRequest.getMaVoucher())
                .ngayBatDau(createMaGiamGiaRequest.getNgayBatDau())
                .ngayKetThuc(createMaGiamGiaRequest.getNgayKetThuc())
                .mota(createMaGiamGiaRequest.getMota())
                .soTienYeuCau(createMaGiamGiaRequest.getSoTienYeuCau())
                .hinhThuckhuyenmai(createMaGiamGiaRequest.getHinhThuckhuyenmai())
                .hinhThucApDung(createMaGiamGiaRequest.getHinhThucApDung())
                .giaTriDuocGiam(createMaGiamGiaRequest.getGiaTriDuocGiam())
                .soLuongMaGianGia(createMaGiamGiaRequest.getSoLuongMaGianGia())
                .build();
    }

    @Override
    public MaGiamGiaEntity mappingByUpdateRequest(UpdateMaGiamGiaRequest updateMaGiamGiaRequest) {
        return MaGiamGiaEntity.builder()
                .maVoucher(updateMaGiamGiaRequest.getMaVoucher())
                .ngayBatDau(updateMaGiamGiaRequest.getNgayBatDau())
                .ngayKetThuc(updateMaGiamGiaRequest.getNgayKetThuc())
                .mota(updateMaGiamGiaRequest.getMota())
                .soTienYeuCau(updateMaGiamGiaRequest.getSoTienYeuCau())
                .hinhThuckhuyenmai(updateMaGiamGiaRequest.getHinhThuckhuyenmai())
                .hinhThucApDung(updateMaGiamGiaRequest.getHinhThucApDung())
                .giaTriDuocGiam(updateMaGiamGiaRequest.getGiaTriDuocGiam())
                .soLuongMaGianGia(updateMaGiamGiaRequest.getSoLuongMaGianGia())
                .build();
    }

    @Override
    public MaGiamGiaReponse mappingByResponse(MaGiamGiaEntity maGiamGiaEntity) {
        return MaGiamGiaReponse.builder()
                .maVoucher(maGiamGiaEntity.getMaVoucher())
                .ngayBatDau(maGiamGiaEntity.getNgayBatDau())
                .ngayKetThuc(maGiamGiaEntity.getNgayKetThuc())
                .giaTriDuocGiam(maGiamGiaEntity.getGiaTriDuocGiam())
                .soLuongMaGianGia(maGiamGiaEntity.getSoLuongMaGianGia())
                .build();
    }

    @Override
    public MaGiamGiaDetailReponse mappingResponseDetail(MaGiamGiaEntity maGiamGiaEntity) {
        return MaGiamGiaDetailReponse.builder()
                .maVoucher(maGiamGiaEntity.getMaVoucher())
                .ngayBatDau(maGiamGiaEntity.getNgayBatDau())
                .ngayKetThuc(maGiamGiaEntity.getNgayKetThuc())
                .giaTriDuocGiam(maGiamGiaEntity.getGiaTriDuocGiam())
                .soLuongMaGianGia(maGiamGiaEntity.getSoLuongMaGianGia())
                .build();
    }
}
