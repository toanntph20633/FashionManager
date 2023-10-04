package com.example.fashionmanager.dto.employee.response;

import com.example.fashionmanager.dto.user.response.UserResponse;
import com.example.fashionmanager.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;

    private String employeeName;

    private String citizenIdentificationCard;

    private String phoneNumber;

    private String city;

    private String district;

    private boolean gender;

    private Boolean active ;

    private String email;

    private String userName; // Thêm trường userName

    private String password; // Thêm trường password
//    private UserResponse userResponse;

}
