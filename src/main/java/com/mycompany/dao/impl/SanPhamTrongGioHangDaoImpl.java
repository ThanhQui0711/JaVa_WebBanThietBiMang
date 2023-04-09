package com.mycompany.dao.impl;

import com.mycompany.dao.SanPhamTrongGioHangDao;
import com.mycompany.entity.SanPhamTrongGioHang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SanPhamTrongGioHangDaoImpl implements SanPhamTrongGioHangDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SanPhamTrongGioHang getSanPhamTrongGioHang(Integer id) {
        Session currentSession = sessionFactory.openSession();
        Query<SanPhamTrongGioHang> query = currentSession.createQuery("SELECT s FROM SanPhamTrongGioHang s WHERE s.maspgh = ?1", SanPhamTrongGioHang.class);
        query.setParameter(1, id);
        List<SanPhamTrongGioHang> resultList = query.getResultList();
        currentSession.close();
        return resultList.stream().findFirst().orElse(null);
    }

    @Override
    public void saveSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.saveOrUpdate(sanPhamTrongGioHang);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        } finally {
            currentSession.close();
        }
    }

    @Override
    public void deleteSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.delete(sanPhamTrongGioHang);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw ex;
        } finally {
            currentSession.close();
        }
    }

    @Override
    public List<SanPhamTrongGioHang> findByUsername(String username) {
        Session session = this.sessionFactory.openSession();
        Query<SanPhamTrongGioHang> query = session.createQuery("SELECT s FROM SanPhamTrongGioHang s WHERE s.nguoiMua.username = :username", SanPhamTrongGioHang.class);
        query.setParameter("username", username);
        List<SanPhamTrongGioHang> resultList = query.getResultList();
        session.close();
        return resultList;
    }
    
    @Override
    public Optional<SanPhamTrongGioHang> findByUsernameAndMasp(String username, Integer masp) {
    	Session session = this.sessionFactory.openSession();
        Query<SanPhamTrongGioHang> query = session.createQuery("SELECT s FROM SanPhamTrongGioHang s WHERE s.nguoiMua.username = :username AND s.sanPham.masp = :masp", SanPhamTrongGioHang.class);
        query.setParameter("username", username);
        query.setParameter("masp", masp);
        List<SanPhamTrongGioHang> resultList = query.getResultList();
        session.close();
        return resultList.stream().findFirst();
    }
}