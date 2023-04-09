package com.mycompany.dao.impl;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.constant.TrangThaiEnum;
import com.mycompany.dao.SanPhamDao;
import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.SanPham;

@Repository
public class SanPhamDaoimpl implements SanPhamDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginationResponse<SanPham> findAll(PaginationRequest<SanPhamDTO> pageable) {
        StringBuilder sql = new StringBuilder("SELECT sp.* FROM san_pham sp WHERE 1 = 1");
        StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM");
        SanPhamDTO dataSearch = pageable.getData();
        Map<String, Object> params = new HashMap<>();
        if (Objects.nonNull(dataSearch.getTenSanPham())) {
            sql.append(" AND sp.ten_san_pham LIKE :tenSanPham");
            params.put("tenSanPham", dataSearch.getTenSanPham());
        }

        if (Objects.nonNull(dataSearch.getTrangThai())) {
            sql.append(" AND sp.trang_thai = :trangThai");
            params.put("trangThai", dataSearch.getTrangThai());
        }

        if (Objects.nonNull(dataSearch.getMaDanhMuc())) {
            sql.append(" AND sp.ma_danh_muc = :maDanhMuc");
            params.put("maDanhMuc", dataSearch.getMaDanhMuc());
        }

        sqlCount.append("( ").append(sql).append(" ) as counter");
        sql.append(" ORDER BY sp.masp DESC");

        Session session = this.sessionFactory.openSession();
        NativeQuery<SanPham> nativeQuery = session.createNativeQuery(sql.toString(), SanPham.class);
        params.forEach(nativeQuery::setParameter);

        NativeQuery<BigInteger> nativeQueryCount = session.createNativeQuery(sqlCount.toString());
        params.forEach(nativeQueryCount::setParameter);

        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.setFirstResult(((pageable.getPage() - 1) * pageable.getPageSize()));
        List<SanPham> resultList = nativeQuery.getResultList();
        BigInteger counter = nativeQueryCount.getSingleResult();
        session.close();;
        return new PaginationResponse<>(
                pageable.getPage(),
                counter.longValue(),
                resultList
        );
    }

    @Override
    public List<SanPham> findByCategoryId(Long categoryId) {
        Session session = this.sessionFactory.openSession();
        Query<SanPham> query = session.createQuery("SELECT sp FROM SanPham sp WHERE sp.danhMuc.id = ?1", SanPham.class);
        query.setParameter(1, categoryId);
        List<SanPham> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List<SanPham> getSanPhams() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<SanPham> theQuery = currentSession.createQuery("SELECT s FROM SanPham s", SanPham.class);
        List<SanPham> sanPham = theQuery.getResultList();
        return sanPham;
    }

    @Override
    public SanPham getSanPham(int sanphamId) {
        Session currentSession = sessionFactory.openSession();
        SanPham sanPham = currentSession.get(SanPham.class, sanphamId);
        currentSession.close();
        return sanPham;
    }

    @Override
    public void saveSanPham(SanPham sanPham) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(sanPham);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSanPham(SanPham sanPham) {
        sanPham.setTrangThai(TrangThaiEnum.INACTIVE.getValue());
        this.saveSanPham(sanPham);
    }
}
