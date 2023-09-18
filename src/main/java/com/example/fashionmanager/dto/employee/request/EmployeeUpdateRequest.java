package com.example.fashionmanager.dto.employee.request;

import com.example.fashionmanager.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeUpdateRequest extends EmployeeCreateRequest {
    @NotNull(message = "Không để trống id")
    private Long id;


    EmployeeUpdateRequest(@NotNull(message = "Không để trống ") @NotBlank(message = "Không để trống ") String employeeName, @NotNull(message = "Không để trống ") @NotBlank(message = "Không để trống ") String citizenIdentificationCard, @NotNull(message = "Không để trống ") @NotBlank(message = "Không để trống ") @Pattern(regexp = "^(05|03|09|08)[0-9]*$", message = "Số điện thoại không hợp lệ") @Size(min = 10, max = 12, message = "Số điện thoại phải có từ 10 đến 12 số") String phoneNumber, @NotNull(message = "Không để trống ") @NotBlank(message = "Không để trống ") String city, @NotNull(message = "Không để trống ") @NotBlank(message = "Không để trống ") String district, @NotNull(message = "Không để trống ") boolean gender, @NotNull(message = "Không để trống ")  UserEntity userEntity) {
        super(employeeName, citizenIdentificationCard, phoneNumber, city, district, gender, userEntity);
    }
}
