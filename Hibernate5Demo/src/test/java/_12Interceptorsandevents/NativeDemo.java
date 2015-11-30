package _12Interceptorsandevents;

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
    public void fetchByQueries() {
        try (Session session = sessionFactory.openSession()) {
            String queries = "select e from  Book e join fetch e.auther";
            Book book = (Book) session.createQuery(queries).uniqueResult();
        }
    }

    @Test
    public void fetchByporfiles() {
        try (Session session = sessionFactory.openSession()) {
            Book book = (Book) session.byId(Book.class).load(1l);
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
