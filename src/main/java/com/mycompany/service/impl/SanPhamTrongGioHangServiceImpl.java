package com.mycompany.service.impl;

import com.mycompany.constant.TrangThaiEnum;
import com.mycompany.dao.HoaDonDao;
import com.mycompany.dao.SanPhamDao;
import com.mycompany.dao.SanPhamTrongGioHangDao;
import com.mycompany.dto.Result;
import com.mycompany.entity.*;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.SanPhamTrongGioHangService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SanPhamTrongGioHangServiceImpl implements SanPhamTrongGioHangService {
    @Autowired
    private SanPhamTrongGioHangDao sanPhamTrongGioHangDao;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Result<SanPhamTrongGioHang> deleteSanPhamTrongGioHang(int theId) {
        Result<SanPhamTrongGioHang> result = new Result<>();
        SanPhamTrongGioHang sanPhamTrongGioHang = this.sanPhamTrongGioHangDao.getSanPhamTrongGioHang(theId);

        KhachHang currentUser = this.khachHangService.getCurrentUser();

        if (!currentUser.getUsername().equals(sanPhamTrongGioHang.getNguoiMua().getUsername())) {
            throw new IllegalStateException();
        }

        try {

            if (Objects.isNull(sanPhamTrongGioHang)) {
                throw new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng");
            }

            sanPhamTrongGioHangDao.deleteSanPhamTrongGioHang(sanPhamTrongGioHang);
            result.setMessage("Xóa thành công");
            result.setError(false);
        } catch (Exception ex) {
            result.setError(true);
            result.setMessage(ex.getMessage());
        }

        return result;
    }

    @Override
    public List<SanPhamTrongGioHang> findAll() {
        KhachHang currentUser = this.khachHangService.getCurrentUser();
        return this.sanPhamTrongGioHangDao.findByUsername(currentUser.getUsername());
    }

    @Override
    public Result<SanPhamTrongGioHang> congSoLuong(Integer id) {
        SanPhamTrongGioHang sanPhamTrongGioHang = this.sanPhamTrongGioHangDao.getSanPhamTrongGioHang(id);
        return this.updateSoLuong(sanPhamTrongGioHang, sanPhamTrongGioHang.getSoLuong() + 1);
    }

    @Override
    public Result<SanPhamTrongGioHang> truSoLuong(Integer id) {
        SanPhamTrongGioHang sanPhamTrongGioHang = this.sanPhamTrongGioHangDao.getSanPhamTrongGioHang(id);
        return this.updateSoLuong(sanPhamTrongGioHang, sanPhamTrongGioHang.getSoLuong() - 1);
    }

    public Result<SanPhamTrongGioHang> updateSoLuong(SanPhamTrongGioHang sanPhamTrongGioHang, Integer soLuong) {
        if (Objects.isNull(sanPhamTrongGioHang)) {
            throw new RuntimeException("khong tim thay san pham");
        }

        KhachHang currentUser = this.khachHangService.getCurrentUser();

        if (!currentUser.getUsername().equals(sanPhamTrongGioHang.getNguoiMua().getUsername())) {
            throw new IllegalStateException();
        }

        Result<SanPhamTrongGioHang> result = new Result<>();
        SanPham sanPham = sanPhamTrongGioHang.getSanPham();

        try {
            if (sanPham.getSoLuongTrongKho() < soLuong) {
                throw new IllegalStateException("Không đủ số lượng sản phẩm");
            }

            sanPhamTrongGioHang.setSoLuong(soLuong);
            this.sanPhamTrongGioHangDao.saveSanPhamTrongGioHang(sanPhamTrongGioHang);
            result.setData(sanPhamTrongGioHang);
            result.setError(false);
            result.setMessage("Cập nhật số lượng thành công");
        } catch (Exception ex) {
            result.setError(true);
            result.setMessage(ex.getMessage());
        }

        return result;
    }

    @Override
    public Result<?> checkout(HoaDon hoaDon) {
        Result<?> result = new Result<>();
        KhachHang currentUser = this.khachHangService.getCurrentUser();
        List<SanPhamTrongGioHang> sanPhams = this.sanPhamTrongGioHangDao.findByUsername(currentUser.getUsername());

        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            if (Objects.isNull(sanPhams) || sanPhams.isEmpty()) {
                throw new IllegalStateException("Giỏ hàng đang trống");
            }

            if (!hoaDon.getSoDienThoai().matches("^0[0-9]{9}") ) {
            	throw new RuntimeException("SDT khong dung dinh dang");
            }
            
            hoaDon.setNgayMua(new Date());
            hoaDon.setNguoiMua(currentUser);

            List<HoaDonChiTiet> chiTiets = sanPhams.stream()
                    .map(x -> {
                        SanPham sp = x.getSanPham();
                        if (x.getSoLuong() > sp.getSoLuongTrongKho()) {
                            throw new RuntimeException("Sản phẩm : " + sp.getTenSanPham() + "không đủ số lượng");
                        }

                        if (TrangThaiEnum.INACTIVE.getValue().equals(sp.getTrangThai())) {
                            throw new RuntimeException("Sản phẩm " + sp.getTenSanPham() + " không còn hoạt động. Vui lòng xóa khơi giỏ hàng");
                        }
                        sp.setSoLuongTrongKho(sp.getSoLuongTrongKho() - x.getSoLuong());
                        session.saveOrUpdate(sp);
                        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                        hoaDonChiTiet.setSanPham(sp);
                        hoaDonChiTiet.setGia(sp.getGiaSanPham());
                        hoaDonChiTiet.setHoaDon(hoaDon);
                        hoaDonChiTiet.setSoLuong(x.getSoLuong());
                        return hoaDonChiTiet;
                    }).collect(Collectors.toList());

            hoaDon.setChiTiets(chiTiets);

            session.save(hoaDon);
            sanPhams.forEach(session::delete);

            transaction.commit();
            result.setError(false);
            result.setMessage("Đặt hàng thành công");
        } catch (Exception ex) {
            transaction.rollback();
            result.setError(true);
            result.setMessage(ex.getMessage());
        } finally {
            session.close();
        }

        return result;
    }
}
