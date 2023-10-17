//package com.example.fashionmanager.service.impl.module_phieu_giam_gia.MaGiamGia;
//import com.example.fashionmanager.dto.ListReponseDto;
//import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaDetailReponse;
//import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaReponse;
//import com.example.fashionmanager.dto.MaGiamGia.Request.CreateMaGiamGiaRequest;
//import com.example.fashionmanager.dto.MaGiamGia.Request.ListMaGiamGiaRequest;
//import com.example.fashionmanager.dto.MaGiamGia.Request.UpdateMaGiamGiaRequest;
//import com.example.fashionmanager.dto.ResponseDto;
//import com.example.fashionmanager.entity.MaGiamGiaEntity;
//import com.example.fashionmanager.repository.MaGiamGiaRepository;
//import com.example.fashionmanager.service.CRUDService;
//import com.example.fashionmanager.service.PhieuGiamGiaService;
//import org.springframework.beans.FatalBeanException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import jakarta.persistence.criteria.Predicate;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service
//import java.util.ArrayList;
//import java.util.List;
//@Service
//public class MaGiamGiaServiceImpl implements PhieuGiamGiaService {
//    @Autowired
//    MaGiamGiaRepository maGiamGiaRepository;
//
//
//    @Override
//    public ResponseEntity<ListReponseDto<MaGiamGiaDetailReponse>> getList(ListMaGiamGiaRequest listMaGiamGiaRequest) {
//        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "dateCreate"), new Sort.Order(Sort.Direction.DESC, "id"));
//
//        Pageable pageable = PageRequest.of(listMaGiamGiaRequest.getPage(), listMaGiamGiaRequest.getSize(), sort);
//
//        Specification<MaGiamGiaEntity> maGiamGiaEntitySpecification = (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (StringUtils.isNotBlank(listMaGiamGiaRequest.getMaVoucher())) {
//                predicates.add(criteriaBuilder.like(root.get("MaVoucher"), "%" + listMaGiamGiaRequest.getMaVoucher() + "%"));
//            }
//            if (StringUtils.isNotBlank(listMaGiamGiaRequest.getMaVoucher())) {
//                predicates.add(criteriaBuilder.like(root.get("ngayBatDau"), "%" + listMaGiamGiaRequest.getMaVoucher() + "%"));
//            }
//            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
//            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
//        };
//        Page<MaGiamGiaEntity> maGiamGiaEntities = maGiamGiaRepository.findAll(maGiamGiaEntitySpecification, pageable);
//        List<MaGiamGiaReponse> maGiamGiaRepone = maGiamGiaEntities.stream().map(maGiamGiaEntity -> MaGiamGiaReponse
//                .builder()
//                .id(maGiamGiaEntity.getId())
//                .maVoucher(maGiamGiaEntity.getMaVoucher())
//               .ngayBatDau(maGiamGiaEntity.getNgayBatDau())
//                .ngayKetThuc(maGiamGiaEntity.getNgayKetThuc())
//                .mota(maGiamGiaEntity.getMota())
//                .soTienYeuCau(maGiamGiaEntity.getSoTienYeuCau())
//                .hinhThuckhuyenmai(maGiamGiaEntity.getHinhThuckhuyenmai())
//                .hinhThucApDung(maGiamGiaEntity.getHinhThucApDung())
//                .giaTriDuocGiam(maGiamGiaEntity.getGiaTriDuocGiam())
//                .soLuongMaGianGia(maGiamGiaEntity.getSoLuongMaGianGia())
//                .build()).toList();
//        ListReponseDto<MaGiamGiaReponse> listReponseDto = new ListReponseDto<MaGiamGiaReponse>();
//        listReponseDto.setItems(maGiamGiaRepone);
//        listReponseDto.setHasNextPage(maGiamGiaEntities.hasNext());
//        listReponseDto.setHasPreviousPage(maGiamGiaEntities.hasPrevious());
//        listReponseDto.setPageCount(maGiamGiaEntities.getTotalPages());
//        listReponseDto.setPageSize(maGiamGiaEntities.getSize());
//
//        return ResponseEntity.ok(listReponseDto);
//    }
//
//    @Override
//    public ResponseEntity<MaGiamGiaReponse> getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<MaGiamGiaDetailReponse> create(CreateMaGiamGiaRequest createMaGiamGiaRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<MaGiamGiaDetailReponse> update(UpdateMaGiamGiaRequest updateMaGiamGiaRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<MaGiamGiaDetailReponse> delete(Long id) {
//        MaGiamGiaEntity maGiamGiaEntity =maGiamGiaRepository.findById(id).map(maGiamGia ->{maGiamGia.setDeleted(true);
//        return maGiamGia;
//        } ).orElseThrow(()->new FatalBeanException(
//                new Exception(
//                        HttpStatus.NOT_FOUND
//                        ,"ma giam gia co id"+ id + "Khong ton tai"
//                )
//        ));
//        ResponseDto<MaGiamGiaReponse>reponseResponseDto= new ResponseDto<>();
//        reponseResponseDto.setContent();
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<MaGiamGiaDetailReponse> changeActive(Long id) {
//        return null;
//    }
//}
