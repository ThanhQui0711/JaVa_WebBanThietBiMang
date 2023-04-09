package com.mycompany.dao;

import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.DanhMuc;

import java.util.List;
import java.util.Optional;

public interface DanhMucDao {

    PaginationResponse<DanhMuc> findAll(PaginationRequest<String> pageable);

    List<DanhMuc> findAll();

    Optional<DanhMuc> findById(Long id);

    DanhMuc save(DanhMuc danhMuc);

    void delete(DanhMuc id);
}
