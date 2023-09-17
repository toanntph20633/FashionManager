package com.example.fashionmanager.controller.admin.category_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.category_manager.request.CategoryCreateRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryListRequest;
import com.example.fashionmanager.dto.category_manager.request.CategoryUpdateRequest;
import com.example.fashionmanager.dto.category_manager.response.CategoryReponse;
import com.example.fashionmanager.service.impl.category_manager.CategoryService;
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
@RequestMapping("/admin/Category-manager")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("list-Category")
    public ListReponseDto<CategoryReponse> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size,
                                                   @RequestParam(value = "active", required = false) Boolean active,
                                                   @RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "code", required = false) String code) {
        CategoryListRequest request = CategoryListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();

        return categoryService.getList(request);

    }
    @PostMapping("create-Category")
    public ResponseDto<CategoryReponse> create(@RequestBody @Valid CategoryCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Thêm thành công");
        }
        return categoryService.save(request);
    }
    @GetMapping("detail-Category/{id}")
    public ResponseDto<CategoryReponse> detail(@PathVariable Long id) {
        return categoryService.detail(id);
    }
    @PutMapping("update-Category/{id}")
    public ResponseDto<CategoryReponse> update(@PathVariable Long id, @RequestBody @Valid CategoryUpdateRequest request, BindingResult bindingResult) {

        request.setId(id);
        return categoryService.update(request);
    }
    @DeleteMapping("delete-Category/{id}")
    public ResponseDto<CategoryReponse> delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
    @PutMapping("change-active-Category/{id}")
    public ResponseDto<CategoryReponse> changeActive(@PathVariable Long id) {
        return categoryService.changeActive(id);
    }
}
