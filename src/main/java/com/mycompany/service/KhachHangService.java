package com.mycompany.service;

import com.mycompany.dto.Result;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.KhachHang;
import org.springframework.validation.BindingResult;

public interface KhachHangService {

    PaginationResponse<KhachHang> findAll(KhachHang khachHang, Integer page);

    Result<KhachHang> lockKhachHang(String username);

    Result<KhachHang> unlockKhachHang(String username);

    Result<KhachHang> saveKhachHang(KhachHang khachHang, BindingResult bindingResult);

    KhachHang getCurrentUser();
}
