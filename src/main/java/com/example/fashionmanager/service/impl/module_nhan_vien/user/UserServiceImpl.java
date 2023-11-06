//package com.example.fashionmanager.service.impl.module_nhan_vien.user;
//
//import com.example.fashionmanager.dto.ListReponseDto;
//import com.example.fashionmanager.dto.nhanvien.quanlynhanvien.response.NhanVienResponse;
//import com.example.fashionmanager.dto.user.response.UserResponse;
//import com.example.fashionmanager.entity.NhanVienEntity;
//import com.example.fashionmanager.entity.UserEntity;
//import com.example.fashionmanager.repository.UserRepository;
//import jakarta.persistence.criteria.Predicate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService{
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserMapper userMapper;
//    @Override
//    public ListReponseDto<UserResponse> getUser(int pageIndex, String userName, Boolean active) {
//
//
//        int pageSize = 10; // Kích thước trang
//        Pageable pageable = PageRequest.of(pageIndex, pageSize);
//
//        Specification<UserEntity> userEntitySpecification = ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if(active == null) {
//                predicates.add(criteriaBuilder.isTrue(root.get("active"))); // Điều kiện active = true
//            }else {
//                predicates.add(criteriaBuilder.equal(root.get("active"), active));
//            }
//            if(userName != null){
//                predicates.add(criteriaBuilder.equal(root.get("userName"),userName));
//            }
//
//
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        });
//
//        Page<UserEntity> userEntities = userRepository.findAll(userEntitySpecification, pageable);
//
//        List<UserResponse> userResponses = userEntities.getContent()
//                .stream()
//                .map(o ->{
//                    UserResponse userResponse = userMapper.convertUserReponse(o);
//
//                    return userResponse;
//                })
//                .collect(Collectors.toList());
//
//        ListReponseDto<UserResponse> listResponseDto = new ListReponseDto<>();
//        listResponseDto.setItems(userResponses);
//        listResponseDto.setHasNextPage(userEntities.hasNext());
//        listResponseDto.setHasPreviousPage(userEntities.hasPrevious());
//        listResponseDto.setPageCount(userEntities.getTotalPages());
//        listResponseDto.setPageSize(pageSize);
//
//        return listResponseDto;
//    }
//}
