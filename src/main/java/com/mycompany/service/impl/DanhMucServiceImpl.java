package com.mycompany.service.impl;

import com.mycompany.dao.DanhMucDao;
import com.mycompany.dao.SanPhamDao;
import com.mycompany.dto.Result;
import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.Pagination;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.DanhMuc;
import com.mycompany.entity.SanPham;
import com.mycompany.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class DanhMucServiceImpl implements DanhMucService {

    private static final Logger logger = Logger.getLogger(DanhMucServiceImpl.class.getName());

    @Autowired
    private DanhMucDao danhMucDao;

    @Autowired
    private SanPhamDao sanPhamDao;

    @Override
    public PaginationResponse<DanhMuc> findAll(PaginationRequest<String> pageable) {
        logger.info("get categories and paging");
        if (Objects.nonNull(pageable.getData())) {
            pageable.setData("%" + pageable.getData() + "%");
        } else {
            pageable.setData("%%");
        }
        if (Objects.isNull(pageable.getPage()) || pageable.getPage() < 1) {
            pageable.setPage(1);
        }

        return this.danhMucDao.findAll(pageable);
    }

    @Override
    public List<DanhMuc> findAll() {
        return this.danhMucDao.findAll();
    }

    @Override
    public DanhMuc findById(Long id) {
        return this.danhMucDao.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Khong tim thay danh muc");
                });
    }

    @Override
    public Result<DanhMuc> save(DanhMuc danhMuc) {
        Result<DanhMuc> result = new Result<>();
        try {
            danhMuc.setNgayTao(new Date());
            DanhMuc save = this.danhMucDao.save(danhMuc);
            result.setData(save);
            result.setError(false);
            result.setMessage("Lưu danh mục thành công");
        } catch (Exception ex) {
            result.setError(true);
            result.setMessage("Lưu danh mục thất bại");
        }
        return result;
    }

    @Override
    public Result<DanhMuc> delete(Long id) {
        Result<DanhMuc> result = new Result<>();
        try {
            DanhMuc danhMuc = this.danhMucDao.findById(id)
                    .orElseThrow(() -> {
                        throw new IllegalArgumentException("Không tồn tại Danh mục: " + id);
                    });

            List<SanPham> categories = this.sanPhamDao.findByCategoryId(id);
            if (!categories.isEmpty()) {
                throw new IllegalArgumentException("Danh mục chứa sản phẩm, không thể xóa.");
            }

            this.danhMucDao.delete(danhMuc);
            result.setMessage("Xóa danh mục thành công");
            result.setError(false);
        } catch (IllegalArgumentException ex) {
            result.setMessage(ex.getMessage());
            result.setError(true);
        } catch (Exception ex) {
            result.setMessage("Xóa danh mục thất bại");
            result.setError(true);
        }

        return result;
    }
}
