package com.example.fashionmanager.controller.employee;


import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeCreateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeListRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.dto.user.request.UserCreateRequest;
import com.example.fashionmanager.entity.RoleEntity;
import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.IEmployeeService;
import com.example.fashionmanager.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IUserService userService;

    @GetMapping("list")
    public ListReponseDto<EmployeeResponse> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "active", defaultValue = "true") Boolean active,
            @RequestParam(value = "employeeName", required = false) String name,
            @RequestParam(value = "citizenIdentificationCard", required = false) String citizenIdentificationCard,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "district", required = false) String district,
            @RequestParam(value = "gender", required = false) Boolean gender
    ) {
        EmployeeListRequest request = EmployeeListRequest.builder()
                .active(active)
                .employeeName(name)
                .citizenIdentificationCard(citizenIdentificationCard)
                .phoneNumber(phoneNumber)
                .city(city)
                .district(district)
                .gender(gender)
                .page(page)
                .size(size)
                .build();

        return employeeService.getList(request);
    }

    @PostMapping("create")
    public ResponseDto<EmployeeResponse> create(
            @RequestBody @Valid EmployeeUserCreateRequest request, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {

            throw new RuntimeException("lỗi");
        }
        // Tạo một UserEntity mới
        UserEntity userEntity = UserEntity.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();


        RoleEntity adminRole = new RoleEntity();
        adminRole.setId(2L); // ID của quyền "admin"

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

        // Lưu EmployeeEntity
        return employeeService.save(employeeCreateRequest);


    }
    @GetMapping("detail/{id}")
    public ResponseDto<EmployeeResponse> detail(@PathVariable Long id) {
        return employeeService.detail(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<EmployeeResponse> update(@PathVariable Long id, @RequestBody @Valid EmployeeUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        request.setId(id);
        return employeeService.update(request);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDto<EmployeeResponse> delete(@PathVariable Long id) {
        return employeeService.delete(id);
    }
    @PutMapping("change-active/{id}")
    public ResponseDto<EmployeeResponse> changeActive(@PathVariable Long id) {
        return employeeService.changeActive(id);
    }
}
