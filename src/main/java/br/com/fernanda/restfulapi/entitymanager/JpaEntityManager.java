package br.com.fernanda.restfulapi.entitymanager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {

    private JpaEntityManager() {
    }

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("product");
        }

        return entityManagerFactory;
    }
}
