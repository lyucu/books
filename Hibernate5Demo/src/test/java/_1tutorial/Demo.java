package _1tutorial;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Event;
import tutorial.Message;

public class Demo {

    private SessionFactory sessionFactory;

    @Test
    public void testXml() {
        // 使用xml配置
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Event("title1", "content1"));
        session.save(new Event("title2", "content2"));
        session.getTransaction().commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getTitle() + ") : " + event.getContent());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testAnnonation() {
        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Message("Our very first event!", "hehe"));
        session.save(new Message("A follow up event", "123"));
        session.getTransaction().commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        for (Event event : (List<Event>) result) {
            System.out.println("Event (" + event.getContent() + ") : " + event.getTitle());
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testJPA() {
        // JPA使用persistent.xml
        // 使用Persistent.xml 里面的 unitName
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my.jpa");
        // create a couple of events...
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Message("Our very first event!", "hehe"));
        entityManager.persist(new Message("A follow up event", "123"));
        entityManager.getTransaction().commit();
        entityManager.close();

        // now lets pull events from the database and list them
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Message> result = entityManager.createQuery("from Message", Message.class).getResultList();
        for (Message event : result) {
            System.out.println("Event (" + event.getContent() + ") : " + event.getTitle());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testEnvers() {
        // 使用@Audited
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my.jpa");
        // create a couple of events...
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Message("Our very first event!", "hehe"));
        entityManager.persist(new Message("A follow up event", "123"));
        entityManager.getTransaction().commit();
        entityManager.close();

        // now lets pull events from the database and list them
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Message> result = entityManager.createQuery("from Message", Message.class).getResultList();
        for (Message event : result) {
            System.out.println("Event (" + event.getContent() + ") : " + event.getTitle());
        }
        entityManager.getTransaction().commit();
        entityManager.close();

        // first lets create some revisions
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Message myMessage = entityManager.find(Message.class, 2L); // we are
                                                                   // using
                                                                   // the
                                                                   // increment
                                                                   // generator,
                                                                   // so we know
                                                                   // 2
                                                                   // is a valid
                                                                   // id
        myMessage.setTitle(myMessage.getTitle() + " (rescheduled)");
        entityManager.getTransaction().commit();
        entityManager.close();

        // and then use an AuditReader to look back through history
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        myMessage = entityManager.find(Message.class, 2L);
        assertEquals("A follow up event (rescheduled)", myMessage.getTitle());
        AuditReader reader = AuditReaderFactory.get(entityManager);
        Message firstRevision = reader.find(Message.class, 2L, 1);
        assertFalse(firstRevision.getTitle().equals(myMessage.getTitle()));
        assertFalse(firstRevision.getContent().equals(myMessage.getContent()));
        Message secondRevision = reader.find(Message.class, 2L, 2);
        assertTrue(secondRevision.getTitle().equals(myMessage.getTitle()));
        assertTrue(secondRevision.getContent().equals(myMessage.getContent()));
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

    @Before
    public void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
                                                                                                  // settings
                                                                                                  // from
                                                                                                  // hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had
            // trouble building the SessionFactory
            // so destroy it manually.
            System.out.println();
            System.out.println(e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @After
    public void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
