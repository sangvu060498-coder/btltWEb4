package com.example.dao.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

// Sửa import theo đúng tên JPAConfig hoặc JPAComfig trong máy bạn:
import com.example.comfig.JPAComfig; 
import com.example.dao.IProductDao;
import com.example.model.Product;

public class ProductDaoImpl implements IProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = JPAComfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = JPAComfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(product);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int productId) throws Exception {
        EntityManager em = JPAComfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product);
            }
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Product findById(int productId) {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            return em.find(Product.class, productId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findTop10Latest() {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.productId DESC", Product.class);
            query.setMaxResults(10);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findAll(int page, int pageSize) {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.productId DESC", Product.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public int count() {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            Long total = em.createNamedQuery("Product.count", Long.class).getSingleResult();
            return total.intValue();
        } finally {
            em.close();
        }
    }
}