package com.mycompany.service.impl;

import com.mycompany.dao.HoaDonDao;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.HoaDon;
import com.mycompany.entity.KhachHang;
import com.mycompany.service.HoaDonService;
import com.mycompany.service.KhachHangService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonDao hoaDonDao;

    @Autowired
    private KhachHangService khachHangService;

    @Override
    public List<HoaDon> findByCurrentUser() {
        KhachHang currentUser = this.khachHangService.getCurrentUser();
        return this.hoaDonDao.getHoaDons(currentUser.getMakh());
    }

    @Override
    public PaginationResponse<HoaDon> findAll(String username, Integer page) {
        PaginationRequest<HoaDon> pageable = new PaginationRequest<>();
        pageable.setPage(page);

        HoaDon hoaDon = new HoaDon();
        KhachHang khachHang = new KhachHang();
        khachHang.setUsername(username);
        if (StringUtils.isNotBlank(username)) {
            khachHang.setUsername("%" + username + "%");
        } else {
            khachHang.setUsername("%%");
        }
        hoaDon.setNguoiMua(khachHang);
        pageable.setData(hoaDon);
        return this.hoaDonDao.findAll(pageable);
    }
}