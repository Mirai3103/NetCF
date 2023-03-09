package DAO.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public  class Context {

    public static EntityManagerFactory getInstance() {
        return instance;
    }
    private static final EntityManagerFactory  instance = Persistence.createEntityManagerFactory("NetCFMySQL");

    private Context() {

    }
}
