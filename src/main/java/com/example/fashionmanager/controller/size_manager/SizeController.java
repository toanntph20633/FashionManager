package com.example.fashionmanager.controller.size_manager;

import com.example.fashionmanager.dto.size_manager.request.SizeCreateRequest;
import com.example.fashionmanager.dto.size_manager.request.SizeUpdateRequest;
import com.example.fashionmanager.entity.SizeEntity;
import com.example.fashionmanager.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/size-manager")
public class SizeController {
    @Autowired
    private ISizeService sizeService;

    @GetMapping("view")
    public List<SizeEntity> size(){
        return sizeService.getAll();
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(sizeService.getOne(id));
        } catch (Exception e) {
           return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("save")
    public String save(@RequestBody SizeCreateRequest sizeCreateRequest){
        sizeService.save(sizeCreateRequest);
        return "Thêm thành công";
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody SizeUpdateRequest sizeUpdateRequest){
        sizeService.update(sizeUpdateRequest);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        sizeService.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}
