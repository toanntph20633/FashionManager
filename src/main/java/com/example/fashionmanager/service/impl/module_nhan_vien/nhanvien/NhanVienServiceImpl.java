package com.example.fashionmanager.service.impl.module_nhan_vien.nhanvien;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUserCreateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;
import com.example.fashionmanager.entity.NhanVienEntity;
import com.example.fashionmanager.entity.RoleEntity;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.repository.NhanVienRepository;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements NhanVienService{
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private NhanVienMapper nhanVienMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ListReponseDto<NhanVienResponse> getActiveEmployees(int pageIndex, Long id, String tenNhanVien, String cccd, String soDienThoai, String diaChi, Boolean gioiTinh) {
        int pageSize = 10; // Kích thước trang
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Specification<NhanVienEntity> employeeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isTrue(root.get("active"))); // Điều kiện active = true

            // Thêm điều kiện tìm kiếm theo tên
            if (tenNhanVien != null && !tenNhanVien.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("tenNhanVien")), "%" + tenNhanVien.toLowerCase() + "%"));
            }
            if (gioiTinh != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("tenNhanVien")), "%" + tenNhanVien.toLowerCase() + "%"));
            }
            if (diaChi != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("tenNhanVien")), "%" + tenNhanVien.toLowerCase() + "%"));
            }

            // Thêm điều kiện tìm kiếm theo căn cước
            if (cccd != null && !cccd.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("cccd"), cccd));
            }

            // Thêm điều kiện tìm kiếm theo số điện thoại
            if (soDienThoai != null && !soDienThoai.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("soDienThoai"), soDienThoai));
            }

            // Thêm điều kiện tìm kiếm theo id
            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        Page<NhanVienEntity> nhanVienEntities = nhanVienRepository.findAll(employeeEntitySpecification, pageable);

        List<NhanVienResponse> nhanVienResponses = nhanVienEntities.getContent()
                .stream()
                .map(o ->{
                    NhanVienResponse nhanVienResponse = nhanVienMapper.getNhanVienResponse(o);
                    UserEntity userEntity = o.getUserEntity();
                    if (userEntity != null) {
                        nhanVienResponse.setEmail(userEntity.getEmail());
                        nhanVienResponse.setPassword(userEntity.getPassword());
                        nhanVienResponse.setUserName(userEntity.getUserName());

                    }
                    return nhanVienResponse;
                })
                .collect(Collectors.toList());

        ListReponseDto<NhanVienResponse> listResponseDto = new ListReponseDto<>();
        listResponseDto.setItems(nhanVienResponses);
        listResponseDto.setHasNextPage(nhanVienEntities.hasNext());
        listResponseDto.setHasPreviousPage(nhanVienEntities.hasPrevious());
        listResponseDto.setPageCount(nhanVienEntities.getTotalPages());
        listResponseDto.setPageSize(pageSize);

        return listResponseDto;
    }

    @Override
    public ResponseDto<NhanVienResponse> save(NhanVienUserCreateRequest request) {
        if(nhanVienRepository.existsByCccdAndDeleted(request.getCccd(), false)){
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Nhân viên đã tồn tại"
                    )
            );
        }

        //Mã hoá mk
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // Tạo một UserEntity mới
        UserEntity userEntity = UserEntity.builder()
                .userName(request.getUserName())
                .password(encodedPassword)
                .email(request.getEmail())
                .build();


        RoleEntity adminRole = new RoleEntity();
        adminRole.setId(1L); // ID của quyền "admin"

        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                .roleEntity(adminRole)
                .userEntity(userEntity)
                .build();

        // Lưu UserEntity và UserRoleEntity
        userRepository.save(userEntity);

        // Lưu UserRoleEntity vào cơ sở dữ liệu
        userRoleRepository.save(userRoleEntity);

        // Tạo một NhanVienEntity và liên kết với UserEntity
        NhanVienEntity nhanVienCreateRequest = NhanVienEntity.builder()
                .tenNhanVien(request.getTenNhanVien())
                .cccd(request.getCccd())
                .soDienThoai(request.getSoDienThoai())
                .diaChi(request.getDiaChi())
                .gioiTinh(request.isGioiTinh())
                .userEntity(userEntity) // Liên kết với UserEntity mới tạo
                .active(true)
                .build();


        ResponseDto<NhanVienResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(nhanVienMapper.getNhanVienResponse(nhanVienRepository.save(nhanVienCreateRequest)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<NhanVienResponse> update(NhanVienUpdateRequest request) {
        if (!nhanVienRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "Nhân viên có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (nhanVienRepository.existsByCccdAndDeletedAndIdNot(request.getCccd(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Nhân viên có số căn cước này đã tồn tại"
                    )
            );
        }
        NhanVienEntity getNhanVienEntity = nhanVienRepository.findById(request.getId()).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + request.getId() + " không tồn tại"
                        )
                )
        );


        // Cập nhật thông tin email trong UserEntity
        UserEntity userEntity = getNhanVienEntity.getUserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setUserName(request.getUserName());
        userEntity.setPassword(request.getPassword());
        userEntity = userRepository.save(userEntity);

        NhanVienEntity nhanVienEntity = nhanVienMapper.getNhanVienEntity(request);
        nhanVienEntity.setUserEntity(userEntity);
        nhanVienEntity.setActive(request.isActive());
        ResponseDto<NhanVienResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(nhanVienMapper.getNhanVienResponse(nhanVienRepository.save(nhanVienEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật thông tin nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<NhanVienResponse> delete(Long id) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findById(id).map(nhanVien -> {
            nhanVien.setDeleted(true);
            nhanVien.setActive(false);
            return nhanVien;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<NhanVienResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(nhanVienMapper.getNhanVienResponse(nhanVienRepository.save(nhanVienEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<NhanVienResponse> detail(Long id) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<NhanVienResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(nhanVienMapper.getNhanVienResponse(nhanVienEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<NhanVienResponse> changeActive(Long id) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findById(id).map(employee -> {
            employee.setActive(!employee.isActive());
            return employee;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<NhanVienResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(nhanVienMapper.getNhanVienResponse(nhanVienRepository.save(nhanVienEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động nhân viên thành công");
        return responseDto;
    }
}
