package com.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import com.example.comfig.JPAComfig;
import com.example.dao.IUserDao;
import com.example.model.User;

public class UserDaoImpl implements IUserDao {

    @Override
    public void insert(User user) {
        EntityManager em = JPAComfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAComfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public User findByEmail(String email) {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public User findById(int id) {
        EntityManager em = JPAComfig.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
}