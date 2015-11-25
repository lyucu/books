package _4persistencecontexts;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Auther;
import tutorial.Book;
import tutorial.Message;

public class NativeDemo {
    SessionFactory sessionFactory;

    @Test
    public void makePersistent() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Message m1 = new Message("h1", "h2");
            session.save(m1);
            session.getTransaction().commit();
        }
    }

    @Test
    public void deleteEntities() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Message m1 = new Message("h1", "h2");
            session.save(m1);
            session.delete(m1);
            session.getTransaction().commit();
        }
    }

    @Test
    public void gerReferenceWithOutInit() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Auther a = new Auther();
            a.setName("auther1");
            session.save(a);
            Book book = new Book();
            book.setAuther(session.byId(Auther.class).getReference(1l));
            session.save(book);
            session.getTransaction().commit();
        }
    }

    @Test
    public void getEntityWithDataInit() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Auther a = session.byId(Auther.class).load(1l);
            session.getTransaction().commit();
        }
    }

    @Test
    public void loadBySimpleNatureId() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Auther a = new Auther();
            a.setnId(111l);
            session.persist(a);

            Auther b = session.bySimpleNaturalId(Auther.class).load(111l);
            System.out.println(b.getnId());
            session.getTransaction().commit();
        }

    }

    @Test
    public void loadByNatureId() {
        try (Session session = sessionFactory.openSession()) {
            // can use .using().using().using().....
            Auther b = session.byNaturalId(Auther.class).using("nId", 111l).load();
            System.out.println(b.getnId());
        }
    }

    @Test
    public void flush() {
        try (Session session = sessionFactory.openSession()) {
            // can use .using().using().using().....
            Auther b = session.byNaturalId(Auther.class).using("nId", 111l).load();
            session.flush();
            System.out.println(b.getnId());
        }

    }

    @Test
    public void mergeDetachedEntity() {
        try (Session session = sessionFactory.openSession()) {
            // can use .using().using().using().....
            Auther a = new Auther();
            a.setId(1l);
            Auther b = (Auther) session.merge(a);
            System.out.println(b.getName());
        }

    }

    @Test
    public void checkEntityPersistentStates() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Auther a = session.byId(Auther.class).load(1l);
            session.getTransaction().commit();
            assert session.contains(a);

            System.out.println(Hibernate.isInitialized(a));

            Book book = new Book();
            book.setAuther(session.byId(Auther.class).getReference(1l));

            System.out.println(Hibernate.isInitialized(book.getAuther()));
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
