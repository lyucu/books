package _14Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NativeDemo {
    SessionFactory sessionFactory;

    @Test
    public void select() {
        try (Session session = sessionFactory.openSession()) {

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
