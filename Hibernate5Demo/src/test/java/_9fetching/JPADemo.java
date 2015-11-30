package _9fetching;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Book;

public class JPADemo {
    EntityManagerFactory entityManagerFactory;

    @Test
    public void fetchByQueries() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root);
        Book b = entityManager.createQuery(criteria).getSingleResult();
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
