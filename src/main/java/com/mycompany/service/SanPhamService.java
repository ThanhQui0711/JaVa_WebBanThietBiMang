package com.mycompany.service;

import com.mycompany.dto.Result;
import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.SanPham;
import com.mycompany.entity.SanPhamTrongGioHang;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SanPhamService {

    PaginationResponse<SanPham> findAll(SanPhamDTO sanPhamDTO, Integer page);

    SanPham getSanPham(int sanphamId);

    Result<SanPham> saveSanPham(SanPham sanPham, MultipartFile hinhAnh);

    Result<SanPham> deleteSanPham(int theId);

    Result<SanPhamTrongGioHang> addToCard(Integer masp, Integer soLuong);
}
