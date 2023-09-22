package com.example.fashionmanager.dto.employee.request;

import com.example.fashionmanager.entity.UserEntity;
import com.example.fashionmanager.entity.UserRoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class EmployeeUserCreateRequest {
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String employeeName;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String citizenIdentificationCard;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    @Pattern(regexp = "^(05|03|09|08)[0-9]*$", message = "Số điện thoại không hợp lệ")
    @Size(min = 10, max = 12, message = "Số điện thoại phải có từ 10 đến 12 số")
    private String phoneNumber;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String city;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String district;
    @NotNull(message = "Không để trống ")
    private boolean gender;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String userName;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String password;
    @NotNull(message = "Không để trống ")
    @NotBlank(message = "Không để trống ")
    private String email;

}
