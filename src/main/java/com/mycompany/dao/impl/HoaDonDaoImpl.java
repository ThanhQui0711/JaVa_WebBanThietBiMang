package com.mycompany.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.dao.HoaDonDao;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.HoaDon;
import com.mycompany.entity.KhachHang;

@Repository
public class HoaDonDaoImpl implements HoaDonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<HoaDon> getHoaDons(int khachhangId) {
        Session currentSession = sessionFactory.openSession();
        Query<HoaDon> theQuery = currentSession.createQuery("SELECT hd FROM HoaDon hd WHERE hd.nguoiMua.makh = ?1", HoaDon.class);
        theQuery.setParameter(1, khachhangId);
        List<HoaDon> hoaDon = theQuery.getResultList();
        currentSession.close();
        return hoaDon;
    }

    @Override
    public PaginationResponse<HoaDon> findAll(PaginationRequest<HoaDon> pageable) {
        Session session = this.sessionFactory.openSession();

        StringBuilder sql = new StringBuilder("SELECT hd.* FROM hoa_don hd")
                .append(" JOIN users u ON u.username = hd.username")
                .append(" WHERE :username IS NULL OR u.username LIKE :username OR u.ten_khach_hang LIKE :username");

        StringBuilder sqlCounter = new StringBuilder("SELECT COUNT(*) FROM (")
                .append(sql).append(") as COUNTER");

        NativeQuery<HoaDon> nativeQuery = session.createNativeQuery(sql.toString(), HoaDon.class);
        NativeQuery<BigInteger> nativeCounter = session.createNativeQuery(sqlCounter.toString());

        Optional.of(pageable.getData())
                .map(HoaDon::getNguoiMua)
                .map(KhachHang::getUsername)
                .ifPresent(username -> {
                    nativeQuery.setParameter("username", username);
                    nativeCounter.setParameter("username", username);

                });

        nativeQuery.setMaxResults(pageable.getPageSize());
        nativeQuery.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());

        List<HoaDon> resultList = nativeQuery.getResultList();
        BigInteger singleResult = nativeCounter.getSingleResult();

        session.close();

        return new PaginationResponse<>(
                pageable.getPage(),
                singleResult.longValue(),
                resultList
        );
    }
}
