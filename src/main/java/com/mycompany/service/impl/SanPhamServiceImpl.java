package com.mycompany.service.impl;

import com.mycompany.constant.TrangThaiEnum;
import com.mycompany.dao.SanPhamDao;
import com.mycompany.dao.SanPhamTrongGioHangDao;
import com.mycompany.dto.Result;
import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.KhachHang;
import com.mycompany.entity.SanPham;
import com.mycompany.entity.SanPhamTrongGioHang;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.SanPhamService;
import com.mycompany.service.SanPhamTrongGioHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamDao sanPhamDao;

    @Autowired
    private SanPhamTrongGioHangDao sanPhamTrongGioHangDao;

    @Autowired
    private KhachHangService khachHangService;
    
    @Autowired
    private SanPhamTrongGioHangService sanPhamTrongGioHangService;
    

    @Override
    public PaginationResponse<SanPham> findAll(SanPhamDTO sanPhamDTO, Integer page) {

        if (Objects.isNull(sanPhamDTO.getTenSanPham())) {
            sanPhamDTO.setTenSanPham("%%");
        } else {
            sanPhamDTO.setTenSanPham("%" + sanPhamDTO.getTenSanPham() + "%");
        }

        PaginationRequest<SanPhamDTO> pageable = new PaginationRequest<>();
        pageable.setData(sanPhamDTO);
        pageable.setPage(page);

        return this.sanPhamDao.findAll(pageable);
    }

    @Override
    public SanPham getSanPham(int sanphamId) {
        SanPham sanPham = sanPhamDao.getSanPham(sanphamId);
        if (Objects.isNull(sanPham)) {
            throw new IllegalArgumentException("Khong tim thay san pham");
        }
        return sanPham;
    }

    @Override
    public Result<SanPham> saveSanPham(SanPham sanPham, MultipartFile hinhAnh) {
        Result<SanPham> result = new Result<>();

        try {

            String tenSanpham = new String(sanPham.getTenSanPham().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String nhaCungCap = new String(sanPham.getNhaCungCap().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String moTa = new String(sanPham.getMoTa().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            sanPham.setTenSanPham(tenSanpham);
            sanPham.setNhaCungCap(nhaCungCap);
            sanPham.setMoTa(moTa);

            if (hinhAnh.getSize() != 0) {
                byte[] bytes = hinhAnh.getBytes();
                sanPham.setImgSp(bytes);
            } else {

                if (Objects.isNull(sanPham.getMasp())) {
                    throw new RuntimeException("Phải thêm hình anh");
                }

                SanPham sp = this.getSanPham(sanPham.getMasp());
                sanPham.setImgSp(sp.getImgSp());
            }

            if (Objects.nonNull(sanPham.getMasp())) {
                SanPham sp = this.getSanPham(sanPham.getMasp());
                sanPham.setTrangThai(sp.getTrangThai());
            }

            sanPham.setTrangThai(TrangThaiEnum.ACTIVE.getValue());
            this.sanPhamDao.saveSanPham(sanPham);
            result.setData(sanPham);
            result.setError(false);
            result.setMessage("Lưu sản phẩm thành công");
        } catch (Exception ex) {
            result.setMessage(ex.getMessage());
            result.setError(true);
        }

        return result;
    }

    @Override
    public Result<SanPham> deleteSanPham(int theId) {
        Result<SanPham> result = new Result<>();
        try {
            SanPham sanPham = this.sanPhamDao.getSanPham(theId);
            if (Objects.isNull(sanPham)) {
                throw new RuntimeException("Khong tim thay san pham: " + theId);
            }
            sanPhamDao.deleteSanPham(sanPham);
            result.setData(sanPham);
            result.setError(false);
            result.setMessage("Xóa sản phẩm thành công");
        } catch (Exception ex) {
            result.setError(true);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @Override
    public Result<SanPhamTrongGioHang> addToCard(Integer masp, Integer soLuong) {
        Result<SanPhamTrongGioHang> result = new Result<>();
        SanPham sanPham = this.getSanPham(masp);

        KhachHang currentUser = this.khachHangService.getCurrentUser();
        try {
            if (sanPham.getSoLuongTrongKho() < soLuong) {
                throw new RuntimeException("Sản phẩm không đủ số lượng yêu cầu");
            }
            
            Optional<SanPhamTrongGioHang> sp = this.sanPhamTrongGioHangDao.findByUsernameAndMasp(currentUser.getUsername(), masp);
            if (sp.isPresent()) {
            	SanPhamTrongGioHang spInCart = sp.get();
            	return this.sanPhamTrongGioHangService.updateSoLuong(sp.get(), spInCart.getSoLuong() + soLuong);
            } 
            SanPhamTrongGioHang sanPhamTrongGioHang = new SanPhamTrongGioHang();
            sanPhamTrongGioHang.setSanPham(sanPham);
            sanPhamTrongGioHang.setSoLuong(soLuong);
            sanPhamTrongGioHang.setNguoiMua(currentUser);

            this.sanPhamTrongGioHangDao.saveSanPhamTrongGioHang(sanPhamTrongGioHang);            	

            result.setError(false);
            result.setMessage("Thêm vào giỏ hàng thành công");
        } catch (Exception exception) {
            result.setError(true);
            result.setMessage(exception.getMessage());
        }
        return result;
    }
}
