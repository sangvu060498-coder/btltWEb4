package com.example.comfig;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAComfig {
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("jpa-data");
        }
        return factory.createEntityManager();
    }

    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}