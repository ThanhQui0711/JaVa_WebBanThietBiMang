package com.mycompany.dao;

import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.KhachHang;

import java.util.Optional;

public interface KhachHangDao {

    PaginationResponse<KhachHang> findAll(PaginationRequest<KhachHang> pageable);

    Optional<KhachHang> findByUsername(String username);

    void saveKhachHang(KhachHang khachHang, Boolean saveAuth);

    Optional<KhachHang> findByPhone(String phone);

    Optional<KhachHang> findByEmail(String email);
}
