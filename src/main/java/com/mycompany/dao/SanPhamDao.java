package com.mycompany.dao;

import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.SanPham;

import java.util.List;

public interface SanPhamDao {

    PaginationResponse<SanPham> findAll(PaginationRequest<SanPhamDTO> pageable);

    List<SanPham> findByCategoryId(Long categoryId);

    List<SanPham> getSanPhams();

    SanPham getSanPham(int sanphamId);

    void saveSanPham(SanPham sanPham);

    void deleteSanPham(SanPham sanPham);
}
