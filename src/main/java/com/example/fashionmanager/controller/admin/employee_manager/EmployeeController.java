package com.example.fashionmanager.controller.admin.employee_manager;


import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.employee.request.EmployeeUpdateRequest;
import com.example.fashionmanager.dto.employee.request.EmployeeUserCreateRequest;
import com.example.fashionmanager.dto.employee.response.EmployeeResponse;
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

    public ListReponseDto<EmployeeResponse> getActiveEmployees(
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "employeeName", required = false) String employeeName,
            @RequestParam(value = "citizenIdentificationCard", required = false) String citizenIdentificationCard,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "id", required = false) Long id
    ) {
        return employeeService.getActiveEmployees(pageIndex, employeeName, citizenIdentificationCard, phoneNumber, id);
    }


    @PostMapping("create")
    public ResponseDto<EmployeeResponse> create(
            @RequestBody @Valid EmployeeUserCreateRequest request, BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Thiếu thông tin");
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
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Thiếu thông tin");
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
