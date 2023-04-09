package com.mycompany.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.dao.KhachHangDao;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.KhachHang;

@Repository
public class KhachHangDaoImpl implements KhachHangDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveKhachHang(KhachHang khachHang, Boolean saveAuth) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.saveOrUpdate(khachHang);
            if (saveAuth) {
                NativeQuery nativeQuery = currentSession.createNativeQuery("insert into authorities (username, authority) values (?1, ?2)");
                nativeQuery.setParameter(1, khachHang.getUsername());
                nativeQuery.setParameter(2, "USER");
                nativeQuery.executeUpdate();
            }
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            currentSession.close();
        }
    }

    @Override
    public PaginationResponse<KhachHang> findAll(PaginationRequest<KhachHang> pageable) {
        StringBuilder sql = new StringBuilder("SELECT u.* FROM users u")
                .append(" JOIN authorities a ON u.username = a.username")
                .append(" WHERE a.authority <> 'ADMIN'")
                .append(" AND (:name IS NULL OR u.ten_khach_hang LIKE :name OR u.username LIKE :name)")
                .append(" AND (:status IS NULL OR u.enabled = :status)");

        StringBuilder sqlCounter = new StringBuilder("SELECT COUNT(*) FROM (")
                .append(sql)
                .append(") as COUNTER");
        sql.append(" ORDER BY ngayTao DESC");
        KhachHang dataSearch = pageable.getData();
        Map<String, Object> params = new HashMap<>();
        params.put("name", dataSearch.getTenKhachHang());
        params.put("status", dataSearch.getEnabled());
        Session session = this.sessionFactory.openSession();
        NativeQuery<KhachHang> nativeQuery = session.createNativeQuery(sql.toString(), KhachHang.class);
        NativeQuery<BigInteger> nativeQueryCounter = session.createNativeQuery(sqlCounter.toString());
        params.forEach((key, value) -> {
            nativeQueryCounter.setParameter(key, value);
            nativeQuery.setParameter(key, value);
        });

        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());

        BigInteger singleResult = nativeQueryCounter.getSingleResult();
        List<KhachHang> resultList = nativeQuery.getResultList();
        session.close();

        return new PaginationResponse<>(
                pageable.getPage(),
                singleResult.longValue(),
                resultList
        );
    }

    @Override
    public Optional<KhachHang> findByUsername(String username) {
        Session session = this.sessionFactory.openSession();
        Query<KhachHang> query = session.createQuery("SELECT kh FROM KhachHang kh WHERE kh.username = ?1", KhachHang.class);
        query.setParameter(1, username);
        List<KhachHang> resultList = query.getResultList();
        session.close();
        return resultList.stream().findFirst();
    }

    @Override
    public Optional<KhachHang> findByPhone(String phone) {
        Session session = this.sessionFactory.openSession();
        Query<KhachHang> query = session.createQuery("SELECT kh FROM KhachHang kh WHERE kh.soDienThoaikh = ?1", KhachHang.class);
        query.setParameter(1, phone);
        List<KhachHang> resultList = query.getResultList();
        session.close();
        return resultList.stream().findFirst();
    }

    @Override
    public Optional<KhachHang> findByEmail(String email) {
        Session session = this.sessionFactory.openSession();
        Query<KhachHang> query = session.createQuery("SELECT kh FROM KhachHang kh WHERE kh.email = ?1", KhachHang.class);
        query.setParameter(1, email);
        List<KhachHang> resultList = query.getResultList();
        session.close();
        return resultList.stream().findFirst();
    }
}
