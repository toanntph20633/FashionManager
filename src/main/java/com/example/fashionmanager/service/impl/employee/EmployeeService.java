package com.example.fashionmanager.service.impl.employee;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.entity.NhanVienEntity;
import com.example.fashionmanager.entity.RoleEntity;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.employee.EmployeeMapper;
import com.example.fashionmanager.repository.EmployeeRepository;
import com.example.fashionmanager.repository.RoleRepository;
import com.example.fashionmanager.repository.UserRepository;
import com.example.fashionmanager.repository.UserRoleRepository;
import com.example.fashionmanager.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ListReponseDto<EmployeeResponse> getActiveEmployees(int pageIndex,String employeeName,
                                                               String citizenIdentificationCard,
                                                               String phoneNumber,
                                                               Long id) {
        int pageSize = 10; // Kích thước trang
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Specification<NhanVienEntity> employeeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isTrue(root.get("active"))); // Điều kiện active = true

            // Thêm điều kiện tìm kiếm theo tên
            if (employeeName != null && !employeeName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("employeeName")), "%" + employeeName.toLowerCase() + "%"));
            }

            // Thêm điều kiện tìm kiếm theo căn cước
            if (citizenIdentificationCard != null && !citizenIdentificationCard.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("citizenIdentificationCard"), citizenIdentificationCard));
            }

            // Thêm điều kiện tìm kiếm theo số điện thoại
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
            }

            // Thêm điều kiện tìm kiếm theo id
            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        Page<NhanVienEntity> employeeEntities = employeeRepository.findAll(employeeEntitySpecification, pageable);

        List<EmployeeResponse> employeeResponses = employeeEntities.getContent()
                .stream()
                .map(o ->{
                    EmployeeResponse employeeResponse = employeeMapper.getEmployeeResponse(o);
                    UserEntity userEntity = o.getUserEntity();
                    if (userEntity != null) {
                        employeeResponse.setEmail(userEntity.getEmail());
                        employeeResponse.setPassword(userEntity.getPassword());
                        employeeResponse.setUserName(userEntity.getUserName());
                    }
                    return employeeResponse;
                })
                .collect(Collectors.toList());

        ListReponseDto<EmployeeResponse> listResponseDto = new ListReponseDto<>();
        listResponseDto.setItems(employeeResponses);
        listResponseDto.setHasNextPage(employeeEntities.hasNext());
        listResponseDto.setHasPreviousPage(employeeEntities.hasPrevious());
        listResponseDto.setPageCount(employeeEntities.getTotalPages());
        listResponseDto.setPageSize(pageSize);

        return listResponseDto;

    }

    @Override
    public ResponseDto<EmployeeResponse> save(EmployeeUserCreateRequest request) {
        if(employeeRepository.existsByCitizenIdentificationCardAndDeleted(request.getCitizenIdentificationCard(), false)){
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

        // Tạo một EmployeeEntity và liên kết với UserEntity
        NhanVienEntity employeeCreateRequest = NhanVienEntity.builder()
                .employeeName(request.getEmployeeName())
                .cccd(request.getCitizenIdentificationCard())
                .soDienThoai(request.getPhoneNumber())
                .city(request.getCity())
                .district(request.getDistrict())
                .gender(request.isGender())
                .userEntity(userEntity) // Liên kết với UserEntity mới tạo
                .active(true)
                .build();


        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeRepository.save(employeeCreateRequest)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Tạo nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<EmployeeResponse> update(EmployeeUpdateRequest request) {
        if (!employeeRepository.existsById(request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.NOT_FOUND
                            , "Nhân viên có id = " + request.getId() + " không tồn tại"
                    )
            );
        }
        if (employeeRepository.existsByCitizenIdentificationCardAndDeletedAndIdNot(request.getCitizenIdentificationCard(), false, request.getId())) {
            throw new FashionManagerException(
                    new ErrorResponse(
                            HttpStatus.INTERNAL_SERVER_ERROR
                            , "Nhân viên có số căn cước này đã tồn tại"
                    )
            );
        }
        NhanVienEntity getEmployeeEntity = employeeRepository.findById(request.getId()).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + request.getId() + " không tồn tại"
                        )
                )
        );


        // Cập nhật thông tin email trong UserEntity
        UserEntity userEntity = getEmployeeEntity.getUserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity = userRepository.save(userEntity);

        NhanVienEntity employeeEntity = employeeMapper.getEmployeeEntity(request);
        employeeEntity.setUserEntity(userEntity);
        employeeEntity.setActive(request.isActive());
        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeRepository.save(employeeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật thông tin nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<EmployeeResponse> delete(Long id) {
        NhanVienEntity employeeEntity = employeeRepository.findById(id).map(employee -> {
            employee.setDeleted(true);
            employee.setActive(false);
            return employee;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeRepository.save(employeeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Xóa nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<EmployeeResponse> detail(Long id) {
        NhanVienEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeEntity));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Hiển thị chi tiết nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<EmployeeResponse> changeActive(Long id) {
        NhanVienEntity employeeEntity = employeeRepository.findById(id).map(employee -> {
            employee.setActive(!employee.isActive());
            return employee;
        }).orElseThrow(() -> new FashionManagerException(
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND
                                , "Nhân viên có id = " + id + " không tồn tại"
                        )
                )
        );
        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeRepository.save(employeeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Thay đổi trạng thái hoạt động nhân viên thành công");
        return responseDto;
    }

}
