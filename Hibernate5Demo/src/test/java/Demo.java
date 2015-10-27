import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
