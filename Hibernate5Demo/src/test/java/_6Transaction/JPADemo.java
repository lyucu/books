package _6Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPADemo {
    EntityManagerFactory entityManagerFactory;

    @Test
    public void transaction() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Before
    public void load() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("my.jpa.update");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @After
    public void tear() {
    }

}
