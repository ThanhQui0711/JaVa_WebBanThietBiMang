package com.mycompany.dao;

import com.mycompany.entity.SanPhamTrongGioHang;

import java.util.List;
import java.util.Optional;

public interface SanPhamTrongGioHangDao {

    SanPhamTrongGioHang getSanPhamTrongGioHang(Integer id);

    void saveSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang);

    void deleteSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang);

    List<SanPhamTrongGioHang> findByUsername(String username);
    
    Optional<SanPhamTrongGioHang> findByUsernameAndMasp(String username, Integer masp);
}
