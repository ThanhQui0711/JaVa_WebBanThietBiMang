package com.mycompany.service;

import com.mycompany.dto.Result;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.DanhMuc;

import java.util.List;

public interface DanhMucService {

    PaginationResponse<DanhMuc> findAll(PaginationRequest<String> pageable);

    List<DanhMuc> findAll();

    DanhMuc findById(Long id);

    Result<DanhMuc> save(DanhMuc danhMuc);

    Result<DanhMuc> delete(Long id);
}
