package com.example.fashionmanager.service.impl.module_nhan_vien.nhanvien;

import com.example.fashionmanager.dto.ListReponseDto;
import com.example.fashionmanager.dto.ResponseDto;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUpdateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.request.NhanVienUserCreateRequest;
import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;

public interface NhanVienService {
    ListReponseDto<NhanVienResponse> getActiveEmployees(int pageIndex, Long id,

                                                            String tenNhanVien,

                                                            String cccd,

                                                            String soDienThoai,

                                                            String diaChi,

                                                            Boolean gioiTinh
                                                        );
    ResponseDto<NhanVienResponse> save(NhanVienUserCreateRequest request);

    ResponseDto<NhanVienResponse> update(NhanVienUpdateRequest request);

    ResponseDto<NhanVienResponse> delete(Long id);

    ResponseDto<NhanVienResponse> detail(Long id);
    ResponseDto<NhanVienResponse> changeActive(Long id);

}
