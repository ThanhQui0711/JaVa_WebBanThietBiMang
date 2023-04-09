package com.mycompany.dao.impl;

import com.mycompany.dao.DanhMucDao;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.DanhMuc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class DanhMucDaoImpl implements DanhMucDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PaginationResponse<DanhMuc> findAll(PaginationRequest<String> pageable) {

        Session session = this.sessionFactory.openSession();
        String sql = "SELECT c FROM DanhMuc c WHERE :name IS NULL OR c.tenDanhMuc LIKE :name";
        String sqlCount = "SELECT count(c) FROM DanhMuc c WHERE :name IS NULL OR c.tenDanhMuc LIKE :name";
        Query<DanhMuc> query = session.createQuery(sql, DanhMuc.class);
        query.setParameter("name", pageable.getData());

        Query<Long> queryCount = session.createQuery(sqlCount);
        queryCount.setParameter("name", pageable.getData());

        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());

        List<DanhMuc> resultList = query.getResultList();
        Long singleResult = queryCount.getSingleResult();

        session.close();
        return new PaginationResponse<>(
                pageable.getPage(),
                singleResult.longValue(),
                resultList
        );
    }

    @Override
    public List<DanhMuc> findAll() {
        Session session = this.sessionFactory.openSession();
        List<DanhMuc> result = session.createQuery("SELECT d FROM DanhMuc d", DanhMuc.class).getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<DanhMuc> findById(Long id) {
        Session session = this.sessionFactory.openSession();
        DanhMuc danhMuc = session.get(DanhMuc.class, id);
        session.close();;
        return Optional.ofNullable(danhMuc);
    }

    @Override
    public DanhMuc save(DanhMuc danhMuc) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(danhMuc);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
        return danhMuc;
    }

    @Override
    public void delete(DanhMuc id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(id);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }
}
