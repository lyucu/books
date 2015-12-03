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

    @Test
    public void collectionMember() {
        String hql1 = "select * form Customer c join c.orders o where o.status = 1";
        // same as
        String hql2 = "select * from Cusotmer c in (c.orders) o where o.status = 1";
        // value
        String hql3 = "select i from Product p join p.imagers i where p.id = 123";
        String hql4 = "select value(i) from Product p join p.images i where p.id = 123";

        // key
        String hql5 = "select key(i) from Product p join p.imagers i where p.id = 123";
        String hql6 = "select entry(i) from Product p join p.imagers i where p.id = 123";
        String hql7 = "select sum(i) from Product p join p.imagers i where p.id = 123";
    }

    @Test
    public void expression() {
        String hql1;
        hql1 = "select *c form Cutomer c where c.name = '123''123'";
        hql1 = "select o from Order o where o.num = 123";
        hql1 = "select o from Order o where o.num = 123L";
        hql1 = "select o from Order o where o.num = 123.0";
        hql1 = "select o from Order o where o.num = 123.F";
        hql1 = "select o from Order o where o.num = 123.5e+3";
        hql1 = "select o from Order o where o.num = 123.5e+3F";
    }

    @Test
    public void parameters() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String sql = "update Book b set b.title = ?!";
        entityManager.createQuery(sql).setParameter(1, "t1").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Test
    public void type() {
        String hql = "select p from Payment p where type(p) = CreditCardPayment";
    }

    @Test
    public void caseExpression() {
        String hql;
        hql = "select case c.nickName when null then '<no name>' else c.nickName end from Customer c";

        hql = "select nvl(c.nickname , 'no name') from CustomerName";
        hql = "select isnull(c.nickname, 'no name') from Customer o";
        hql = " select coalesce(c.nickName,'no name') from Customer o";

        hql = " select case when c.name is not null then c.name when c.nickName is not null then c.nickName else 'no name' end from Customer c";
        hql = " select coalesce(c.name , c.nickName, 'no name') from Customer c";

        hql = "select nullif (c.name , c.nickName) from Customer c";
        hql = "select case when c.name = c.nickName then null else c.name end from Customer c";
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
