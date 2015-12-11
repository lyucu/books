package _15nativesql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.transform.Transformers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Book;

public class NativeDemo {
    SessionFactory sessionFactory;

    @Test
    public void scalarQueries() {
        try (Session session = sessionFactory.openSession()) {
            session.createSQLQuery("select * from book").list();
        }
    }

    @Test
    public void entityQueries() {
        try (Session session = sessionFactory.openSession()) {
            List list = session.createSQLQuery("select * from book").addEntity(Book.class).list();
            System.out.println(list.size());
        }
    }

    @Test
    public void associationsAndCollection() {
        // error
        try (Session session = sessionFactory.openSession()) {
            List list = session.createSQLQuery("select * from book b , auther a where b.auther_id = a.id")
                    .addEntity("b", Book.class).addJoin("b", "a").list();
            System.out.println(list.size());
        }

    }

    @Test
    public void multipleEntities() {
        try (Session session = sessionFactory.openSession()) {
            List list = session
                    .createSQLQuery("select {b1.*},{b2.*} from book b1 , book b2 where b1.auther_id = b1.auther_id")
                    .addEntity("b1", Book.class).addEntity("b2", Book.class).list();
            System.out.println(list.size());
        }
    }

    @Test
    public void nonManagedEntities() {
        // error
        try (Session session = sessionFactory.openSession()) {
            List list = session.createSQLQuery("select * from book")
                    .setResultTransformer(Transformers.aliasToBean(Book.class)).list();
            System.out.println(list.size());
        }
    }

    @Test
    public void paraneters() {
        try (Session session = sessionFactory.openSession()) {
            List list = session.createSQLQuery("select * from book where id =? and mtitle =:title")
                    .addEntity(Book.class).setParameter(0, 1).setParameter("title", "t1").list();
            System.out.println(list.size());
        }
    }

    @Test
    public void nameSqlQueries() {
        // errro
        try (Session session = sessionFactory.openSession()) {
            List list = session.getNamedQuery("queryName").setMaxResults(40).list();
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
