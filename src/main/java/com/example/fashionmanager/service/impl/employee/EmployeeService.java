package com.example.fashionmanager.service.impl.employee;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeCreateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeListRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.entity.EmployeeEntity;
import com.example.fashionmanager.entity.RoleEntity;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.enums.ResponseStatus;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.mapping.employee.EmployeeMapper;
import com.example.fashionmanager.repository.EmployeeRepository;
import com.example.fashionmanager.service.IEmployeeService;
import com.example.fashionmanager.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;



@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public ListReponseDto<EmployeeResponse> getList(EmployeeListRequest request) {
        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.DESC,"dateCreate")
                , new Sort.Order(Sort.Direction.DESC,"id")
        );
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize(),sort);
        Specification<EmployeeEntity> employeeEntitySpecification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getEmployeeName())) {
                predicates.add(criteriaBuilder.like(root.get("employeeName"), "%" + request.getEmployeeName() + "%"));
            }

            if (StringUtils.isNotBlank(request.getCitizenIdentificationCard())) {
                predicates.add(criteriaBuilder.like(root.get("citizenIdentificationCard"), "%" + request.getCitizenIdentificationCard() + "%"));
            }

            if (StringUtils.isNotBlank(request.getPhoneNumber())) {
                predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + request.getPhoneNumber() + "%"));
            }

            if (StringUtils.isNotBlank(request.getCity())) {
                predicates.add(criteriaBuilder.equal(root.get("city"), request.getCity()));
            }

            if (StringUtils.isNotBlank(request.getDistrict())) {
                predicates.add(criteriaBuilder.equal(root.get("district"), request.getDistrict()));
            }
            predicates.add(criteriaBuilder.equal(root.get("gender"), request.isGender()));
//            predicates.add(criteriaBuilder.equal(root.get("userResponse"), request.getUserResponse()));

            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            predicates.add(criteriaBuilder.equal(root.get("active"), request.isActive()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<EmployeeEntity> employeeEntities = employeeRepository.findAll(employeeEntitySpecification, pageable);
        List<EmployeeResponse> employeeReponses = employeeEntities.stream().map(employee -> employeeMapper.getEmployeeResponse(employee)).toList();
        ListReponseDto<EmployeeResponse> listReponseDto = new ListReponseDto<EmployeeResponse>();
        listReponseDto.setItems(employeeReponses);
        listReponseDto.setHasNextPage(employeeEntities.hasNext());
        listReponseDto.setHasPreviousPage(employeeEntities.hasPrevious());
        listReponseDto.setPageCount(employeeEntities.getTotalPages());
        listReponseDto.setPageSize(employeeEntities.getSize());
        return listReponseDto;
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
        userService.add(userEntity, userRoleEntity);

        // Tạo một EmployeeEntity và liên kết với UserEntity
        EmployeeCreateRequest employeeCreateRequest = EmployeeCreateRequest.builder()
                .employeeName(request.getEmployeeName())
                .citizenIdentificationCard(request.getCitizenIdentificationCard())
                .phoneNumber(request.getPhoneNumber())
                .city(request.getCity())
                .district(request.getDistrict())
                .gender(request.isGender())
                .userEntity(userEntity)
                .build();

        EmployeeEntity employeeEntity = employeeMapper.getEmployeeEntity(employeeCreateRequest);
        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeRepository.save(employeeEntity)));
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
                            , "Nhân viên có số căn cước = " + request.getId() + " không tồn tại"
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
        EmployeeEntity employeeEntity = employeeMapper.getEmployeeEntity(request);
        ResponseDto<EmployeeResponse> responseDto = new ResponseDto<>();
        responseDto.setContent(employeeMapper.getEmployeeResponse(employeeRepository.save(employeeEntity)));
        responseDto.setStatus(ResponseStatus.SUCCESS);
        responseDto.setMessage("Cập nhật thông tin nhân viên thành công");
        return responseDto;
    }

    @Override
    public ResponseDto<EmployeeResponse> delete(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).map(employee -> {
            employee.setDeleted(true);
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
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new FashionManagerException(
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
        EmployeeEntity employeeEntity = employeeRepository.findById(id).map(employee -> {
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
