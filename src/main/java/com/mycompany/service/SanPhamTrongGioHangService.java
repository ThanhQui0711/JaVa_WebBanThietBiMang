package com.mycompany.service;

import com.mycompany.dto.Result;
import com.mycompany.entity.HoaDon;
import com.mycompany.entity.SanPhamTrongGioHang;

import java.util.List;

public interface SanPhamTrongGioHangService {

    Result<SanPhamTrongGioHang> deleteSanPhamTrongGioHang(int theId);

    List<SanPhamTrongGioHang> findAll();

    Result<SanPhamTrongGioHang> congSoLuong(Integer id);

    Result<SanPhamTrongGioHang> truSoLuong(Integer id);
    
    Result<SanPhamTrongGioHang> updateSoLuong(SanPhamTrongGioHang sanPhamTrongGioHang, Integer soLuong);
    

    Result<?> checkout(HoaDon hoaDon);
}
