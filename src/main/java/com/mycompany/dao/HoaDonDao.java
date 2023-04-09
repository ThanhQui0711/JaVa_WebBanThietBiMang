package com.mycompany.dao;

import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.HoaDon;

import java.util.List;

public interface HoaDonDao {
    List<HoaDon> getHoaDons(int khachhangId);

    PaginationResponse<HoaDon> findAll(PaginationRequest<HoaDon> pageable);
}
