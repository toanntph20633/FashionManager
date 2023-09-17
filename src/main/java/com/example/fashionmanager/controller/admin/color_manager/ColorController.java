package com.example.fashionmanager.controller.admin.color_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.color_manager.request.ColorCreateRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorListRequest;
import com.example.fashionmanager.dto.color_manager.request.ColorUpdateRequest;
import com.example.fashionmanager.dto.color_manager.response.ColorReponse;

import com.example.fashionmanager.service.impl.color_manager.ColorService;
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
@RequestMapping("/admin/color-manager")
public class ColorController {
    @Autowired
    ColorService colorService;
    @GetMapping("list-color")
    public ListReponseDto<ColorReponse> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                @RequestParam(value = "active", required = false) Boolean active,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "code", required = false) String code) {
        ColorListRequest request = ColorListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();

        return colorService.getList(request);

    }
    @PostMapping("create-color")
    public ResponseDto<ColorReponse> create(@RequestBody @Valid ColorCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Thêm thành công");
        }
        return colorService.save(request);
    }
    @GetMapping("detail-color/{id}")
    public ResponseDto<ColorReponse> detail(@PathVariable Long id) {
        return colorService.detail(id);
    }
    @PutMapping("update-color/{id}")
    public ResponseDto<ColorReponse> update(@PathVariable Long id, @RequestBody @Valid ColorUpdateRequest request, BindingResult bindingResult) {

        request.setId(id);
        return colorService.update(request);
    }
    @DeleteMapping("delete-color/{id}")
    public ResponseDto<ColorReponse> delete(@PathVariable Long id) {
        return colorService.delete(id);
    }
    @PutMapping("change-active-color/{id}")
    public ResponseDto<ColorReponse> changeActive(@PathVariable Long id) {
        return colorService.changeActive(id);
    }
}
