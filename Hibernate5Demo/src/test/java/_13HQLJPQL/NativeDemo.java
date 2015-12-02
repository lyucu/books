package _13HQLJPQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Book;

public class NativeDemo {
    SessionFactory sessionFactory;

    @Test
    public void update() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "update Book b set b.title = :title";
            session.createQuery(sql).setString("title", "title").executeUpdate();
        }
    }

    @Test
    public void updateByVersion() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "update  versioned  Auther a set a.name = :name where  a.id = 1";
            session.createQuery(sql).setString("name", "lyc1").executeUpdate();
        }
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
    public void fetchByporfiles() {
        try (Session session = sessionFactory.openSession()) {
            Book book = (Book) session.byId(Book.class).load(1l);
        }
    }

    @Test
    public void inesrt() {
        String hqlInsert = "insert into Book (id) select a.id from Auther a where... ";
        try (Session session = sessionFactory.openSession()) {
            session.createQuery(hqlInsert).executeUpdate();
        }
    }

    @Before
    public void load() {
        try {
            StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();
            StandardServiceRegistry standardRegistry = standardRegistryBuilder.configure().build();
            MetadataSources sources = new MetadataSources(standardRegistry);
            Metadata metadata = sources.buildMetadata();
            sessionFactory = metadata.buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @After
    public void tear() {
        sessionFactory.close();
    }

}
