package com.example.fashionmanager.controller.admin.employee_manager;


import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeListRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
import com.example.fashionmanager.entity.EmployeeEntity;
import com.example.fashionmanager.service.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/super-admin/employee-manager")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;


    @GetMapping("list")
//    public ListReponseDto<EmployeeResponse> getList(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "10") int size,
//            @RequestParam(value = "active", defaultValue = "true") Boolean active,
//            @RequestParam(value = "employeeName", required = false) String name,
//            @RequestParam(value = "citizenIdentificationCard", required = false) String citizenIdentificationCard,
//            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
//            @RequestParam(value = "city", required = false) String city,
//            @RequestParam(value = "district", required = false) String district,
//            @RequestParam(value = "gender", required = false) boolean gender
//    ) {
//        EmployeeListRequest request = EmployeeListRequest.builder()
//                .active(active)
//                .employeeName(name)
//                .citizenIdentificationCard(citizenIdentificationCard)
//                .phoneNumber(phoneNumber)
//                .city(city)
//                .district(district)
//                .gender(gender)
//                .page(page)
//                .size(size)
//                .build();
//
//        return employeeService.getList(request);

//    }
    public ListReponseDto<EmployeeResponse> getActiveEmployees(
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex
    ) {
        return employeeService.getActiveEmployees(pageIndex);
    }


    @PostMapping("create")
    public ResponseDto<EmployeeResponse> create(
            @RequestBody @Valid EmployeeUserCreateRequest request, BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){

        }
        // Lưu EmployeeEntity
        return employeeService.save(request);

    }
    @GetMapping("detail/{id}")
    public ResponseDto<EmployeeResponse> detail(@PathVariable Long id) {
        return employeeService.detail(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<EmployeeResponse> update(@PathVariable Long id, @RequestBody @Valid EmployeeUpdateRequest request, BindingResult bindingResult) {

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
