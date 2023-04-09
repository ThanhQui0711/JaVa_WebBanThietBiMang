package com.mycompany.service;


import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.HoaDon;

import java.util.List;

public interface HoaDonService {

    List<HoaDon> findByCurrentUser();

    PaginationResponse<HoaDon> findAll(String username, Integer page);
}
