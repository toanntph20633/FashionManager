package com.example.fashionmanager.dto.employee.request;

import com.example.fashionmanager.dto.user.response.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeListRequest {
    private int page;

    private int size;

    private String employeeName;

    private String citizenIdentificationCard;

    private String phoneNumber;

    private String city;

    private String district;

    private boolean gender = true ;

    private UserResponse userResponse;

    private boolean active = true;
}
