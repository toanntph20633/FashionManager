package com.example.fashionmanager.controller.admin.rank_manager;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.rank_manager.request.RankCreateRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankListRequest;
import com.example.fashionmanager.dto.rank_manager.request.RankUpdateRequest;
import com.example.fashionmanager.dto.rank_manager.response.RankReponse;
import com.example.fashionmanager.exception.ErrorResponse;
import com.example.fashionmanager.exception.FashionManagerException;
import com.example.fashionmanager.service.IRankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/rank-manager")
public class RankController {
    @Autowired
    private IRankService rankService;

    @GetMapping("list")
    public ListReponseDto<RankReponse> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "active", required = false) Boolean active,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "code", required = false) String code) {
        RankListRequest request = RankListRequest.builder()
                .active(active)
                .code(code)
                .name(name)
                .page(page)
                .size(size)
                .build();

        return rankService.getList(request);
    }

    @PostMapping("create")
    public ResponseDto<RankReponse> create(@RequestBody @Valid RankCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FashionManagerException(ErrorResponse
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(bindingResult.getAllErrors().stream()
                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
                    .build());
        }
        return rankService.save(request);
    }

    @GetMapping("detail/{id}")
    public ResponseDto<RankReponse> detail(@PathVariable Long id) {
        return rankService.detail(id);
    }

    @PutMapping("update/{id}")
    public ResponseDto<RankReponse> update(@PathVariable Long id, @RequestBody @Valid RankUpdateRequest request, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new FashionManagerException(ErrorResponse
//                    .builder()
//                    .status(HttpStatus.BAD_REQUEST)
//                    .message(bindingResult.getAllErrors().stream()
//                            .map(o -> o.getDefaultMessage()).collect(Collectors.toList()).toString())
//                    .build());
//        }
        request.setId(id);
        return rankService.update(request);
    }

    @DeleteMapping("delete/{id}")
    public ResponseDto<RankReponse> delete(@PathVariable Long id) {
        return rankService.delete(id);
    }

    @PutMapping("change-active/{id}")
    public ResponseDto<RankReponse> changeActive(@PathVariable Long id) {
        return rankService.changeActive(id);
    }

}
