package com.example.fashionmanager.service.impl.module_phieu_giam_gia.MaGiamGia;
import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaDetailReponse;
import com.example.fashionmanager.dto.MaGiamGia.Reponse.MaGiamGiaReponse;
import com.example.fashionmanager.dto.MaGiamGia.Request.CreateMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.ListMaGiamGiaRequest;
import com.example.fashionmanager.dto.MaGiamGia.Request.UpdateMaGiamGiaRequest;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.entity.MaGiamGiaEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.MaGiamGiaRepository;
import com.example.fashionmanager.service.CRUDService;
import com.example.fashionmanager.service.impl.module_phieu_giam_gia.MaGiamGia.PhieuGiamGiaService;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class MaGiamGiaServiceImpl implements PhieuGiamGiaService {
    @Autowired
    MaGiamGiaRepository maGiamGiaRepository;
@Autowired
MaGiamGiaMapper mapper;

    @Override
    public ListReponseDto<MaGiamGiaReponse> getList(ListMaGiamGiaRequest request) {
                Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC, "dateCreate")
                , new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<MaGiamGiaEntity> maGiamGiaEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getMaVoucher())) {
                predicates.add(criteriaBuilder.like(root.get("maVoucher"), "%" + request.getMaVoucher() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<MaGiamGiaEntity> maGiamGiaEntities = maGiamGiaRepository.findAll(maGiamGiaEntitySpecification, pageable);
        List<MaGiamGiaReponse> maGiamGiaReponses = maGiamGiaEntities.stream().map(maGiamGia -> mapper.getMaGiamGiaReponse(maGiamGia)).toList();
        ListReponseDto<MaGiamGiaReponse> listReponseDto = new ListReponseDto<MaGiamGiaReponse>();
        listReponseDto.setItems(maGiamGiaReponses);
        listReponseDto.setHasNextPage(maGiamGiaEntities.hasNext());
        listReponseDto.setHasPreviousPage(maGiamGiaEntities.hasPrevious());
        listReponseDto.setPageCount(maGiamGiaEntities.getTotalPages());
        listReponseDto.setPageSize(maGiamGiaEntities.getSize());
        return listReponseDto;
    }

    @Override
    public ResponseDto<MaGiamGiaReponse> save(CreateMaGiamGiaRequest request) {
        if (maGiamGiaRepository.existsByMaVoucherAndDeleted(request.getMaVoucher(), false)) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Mã code đã tồn tại"
                    )
            );
        }
        MaGiamGiaEntity maGiamGiaEntity = mapper.getMaGiamGiaCreate(request);
        ResponseDto<MaGiamGiaReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(mapper.getMaGiamGiaReponse(maGiamGiaRepository.save(maGiamGiaEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo thứ mã giảm giá thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<MaGiamGiaReponse> update(UpdateMaGiamGiaRequest request) {
//        if (!maGiamGiaRepository.existsById(request.getId())) {
//            throw new FashionManagerException(
//                    new ErrorResponse(
//                            HttpStatus.NOT_FOUND
//                            , "Thứ hạng có id = " + request.getId() + " không tồn tại"
//                    )
//            );
//        }
//        if (maGiamGiaRepository.existsByMaVoucherAndDeletedAndIdNot(request.getMaVoucher(), false, request.getId())) {
//            throw new FashionManagerException(
//                    new ErrorResponse(
//                            HttpStatus.INTERNAL_SERVER_ERROR
//                            , "Mã code đã tồn tại"
//                    )
//            );
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate;
//        Date editDate;
//        Date currentDate = new Date();
//
//        try {
//            startDate = dateFormat.parse(ngayBatDau);
//            editDate = dateFormat.parse(ngayChinhSua);
//
//            if (((Date) editDate).before(startDate) || editDate.after(currentDate)) {
//                System.out.println("Không cho phép chỉnh sửa");
//            } else {
//                System.out.println("Cho phép chỉnh sửa");
//            }
//        } catch (Exception e) {
//            System.out.println("Lỗi: Định dạng ngày không hợp lệ");
//        }
//
//        MaGiamGiaEntity maGiamGiaEntity = mapper.getMaGiamGiaUpdate(request);
//        ResponseDto<MaGiamGiaReponse> responseDto = new ResponseDto<>();
//        responseDto.setContent(mapper.getMaGiamGiaReponse(maGiamGiaRepository.save(maGiamGiaEntity)));
//        responseDto.setStatus(ResponseStatus.SUCCESS);
//        responseDto.setMessage("Cập nhật mã giảm giá thành công");
//        return responseDto;
        if (!maGiamGiaRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND,
                            "Thứ hạng có id = " + request.getId() + " không tồn tại"
                    )
            );
        }

        // Kiểm tra xem mã giảm giá với mã voucher đã cung cấp đã tồn tại và chưa bị xóa, ngoại trừ ID đang được cập nhật
        if (maGiamGiaRepository.existsByMaVoucherAndDeletedAndIdNot(request.getMaVoucher(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Mã code đã tồn tại"
                    )
            );
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date editDate;
        Date currentDate = new Date();

        try {
            startDate = dateFormat.parse(String.valueOf(request.getNgayBatDau())); // Sử dụng request.getNgayBatDau thay vì ngayBatDau
            editDate = dateFormat.parse(String.valueOf(request.getNgayKetThuc())); // Sử dụng request.getNgayChinhSua thay vì ngayChinhSua

            if (editDate.before(startDate) || editDate.after(currentDate)) {
                // Nếu ngày chỉnh sửa trước ngày bắt đầu hoặc sau ngày hiện tại, thông báo không cho phép chỉnh sửa
                System.out.println("Không cho phép chỉnh sửa");
            } else {
                // Nếu điều kiện thỏa mãn, cho phép chỉnh sửa
                System.out.println("Cho phép chỉnh sửa");
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi định dạng ngày
            System.out.println("Lỗi: Định dạng ngày không hợp lệ");
        }

        // Tạo thực thể MaGiamGiaEntity dựa trên request
        MaGiamGiaEntity maGiamGiaEntity = mapper.getMaGiamGiaUpdate(request);

        // Lưu thực thể cập nhật vào cơ sở dữ liệu và tạo phản hồi
        ResponseDto<MaGiamGiaReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(mapper.getMaGiamGiaReponse(maGiamGiaRepository.save(maGiamGiaEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật mã giảm giá thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<MaGiamGiaReponse> delete(Long id) {
        MaGiamGiaEntity maGiamGiaEntity = maGiamGiaRepository.findById(id).map(maGiamGia -> {
            maGiamGia.setDeleted(true);
            return maGiamGia;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Mã giảm giá có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<MaGiamGiaReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(mapper.getMaGiamGiaReponse(maGiamGiaRepository.save(maGiamGiaEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa mã giảm giá thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<MaGiamGiaReponse> detail(Long id) {
        MaGiamGiaEntity maGiamGiaEntity = maGiamGiaRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<MaGiamGiaReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(mapper.getMaGiamGiaReponse(maGiamGiaEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị mã giảm giá thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<MaGiamGiaReponse> changeActive(Long id) {
                MaGiamGiaEntity maGiamGiaEntity = maGiamGiaRepository.findById(id).map(maGiamGia -> {
            maGiamGia.setActive(!maGiamGia.isActive());
            return maGiamGia;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Thứ hạng có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<MaGiamGiaReponse> responseDto = new ResponseDto<>();
        responseDto.setContent(mapper.getMaGiamGiaReponse(maGiamGiaRepository.save(maGiamGiaEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái Mã giảm giá thành công");
        return responseDto;
    }
}
