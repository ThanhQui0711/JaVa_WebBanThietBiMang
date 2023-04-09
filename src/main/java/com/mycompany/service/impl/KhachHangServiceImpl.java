package com.mycompany.service.impl;

import com.mycompany.constant.TrangThaiEnum;
import com.mycompany.dao.KhachHangDao;
import com.mycompany.dto.Result;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.KhachHang;
import com.mycompany.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangDao khachHangDao;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<KhachHang> saveKhachHang(KhachHang khachHang, BindingResult bindingResult) {
        Result<KhachHang> result = new Result<>();
        try {

            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                throw new RuntimeException(String.join(",", errors));
            }

            if (this.userDetailsManager.userExists(khachHang.getUsername())) {
                throw new RuntimeException("Tên đăng nhập đã tồn tại!");
            }

            Optional<KhachHang> email = this.khachHangDao.findByEmail(khachHang.getEmail());
            if (email.isPresent()) {
                throw new RuntimeException("Email đã tồn tại!");
            }

            Optional<KhachHang> phone = this.khachHangDao.findByPhone(khachHang.getSoDienThoaikh());
            if (phone.isPresent()) {
                throw new RuntimeException("SĐT đã tồn tại!");
            }

            khachHang.setPassword(this.passwordEncoder.encode(khachHang.getPassword()));
            khachHang.setNgayTao(new Date());
            khachHang.setEnabled(true);

            khachHangDao.saveKhachHang(khachHang, true);
            result.setError(false);
            result.setMessage("Đăng ký thành công");
        } catch (Exception ex) {
            result.setError(true);
            result.setMessage(ex.getMessage());
        }
        return result;
    }


    @Override
    public PaginationResponse<KhachHang> findAll(KhachHang khachHang, Integer page) {
        if (Objects.nonNull(khachHang.getTenKhachHang())) {
            khachHang.setTenKhachHang("%" + khachHang.getTenKhachHang() + "%");
        }
        if (Objects.isNull(page)) {
            page = 1;
        }
        PaginationRequest<KhachHang> pageable = new PaginationRequest<>();
        pageable.setData(khachHang);
        pageable.setPage(page);
        return this.khachHangDao.findAll(pageable);
    }

    @Override
    public Result<KhachHang> lockKhachHang(String username) {
        Result<KhachHang> result = this.changeStatusKhachHang(username, TrangThaiEnum.INACTIVE);
        if (!result.getError()) {
            result.setMessage("Khóa khách khách hàng thành công");
        }
        return result;
    }

    @Override
    public Result<KhachHang> unlockKhachHang(String username) {
        Result<KhachHang> result = this.changeStatusKhachHang(username, TrangThaiEnum.ACTIVE);
        if (!result.getError()) {
            result.setMessage("Mở khóa khách khách hàng thành công");
        }
        return result;
    }

    private Result<KhachHang> changeStatusKhachHang(String username, TrangThaiEnum trangThaiEnum) {
        Result<KhachHang> result = new Result<>();

        try {
            KhachHang khachHang = this.khachHangDao.findByUsername(username)
                    .orElseThrow(() -> {
                        throw new RuntimeException("Không tìm thấy khách hàng: " + username);
                    });
            khachHang.setEnabled(TrangThaiEnum.ACTIVE.equals(trangThaiEnum));
            this.khachHangDao.saveKhachHang(khachHang, false);
            result.setData(khachHang);
            result.setError(false);
        } catch (Exception exception) {
            result.setError(true);
            result.setMessage(exception.getMessage());
        }

        return result;
    }

    @Override
    public KhachHang getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return this.khachHangDao.findByUsername(username)
                .orElseThrow(() -> {
                    throw new IllegalStateException();
                });
    }
}
