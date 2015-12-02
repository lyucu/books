package _13HQLJPQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPADemo {
    EntityManagerFactory entityManagerFactory;

    @Test
    public void update() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String sql = "update Book b set b.title = :title";
        entityManager.createQuery(sql).setParameter("title", "t1").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void from() {
        String hql = "select c from Cat cat1";
        hql = "select c from Cat cat1 , Cat cat2";
    }

    @Test
    public void join() {
        String hql1 = "select * from Book book inner join  book.auther auther where auther.id = 123";
        String hql2 = "select * from Book book left join  book.auther auther where auther.id = 123";
        String hql3 = "select * from Book book right join  book.auther auther where auther.id = 123";
        String hql4 = "select * from Book book right join  fetch book.auther auther where auther.id = 123";
        String hql5 = "select * from Book book where book.auther.id  =  123";
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
