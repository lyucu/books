package _4persistencecontexts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Auther;
import tutorial.Message;

public class JPADemo {
    EntityManagerFactory entityManagerFactory;

    @Test
    public void makePersistent() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Message m1 = new Message("h1", "h2");
        entityManager.persist(m1);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Test
    public void deleteEntities() {
        // obejct must be managed
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Message m1 = new Message("h1", "h2");
        entityManager.persist(m1);
        entityManager.remove(m1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void gerReferenceWithOutInit() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Auther a = new Auther();
        a.setName("auther1");
        entityManager.persist(a);
        // Book book = new Book();
        // book.setAuther(entityManager.getReference(Auther.class, 1l));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void getEntityWithDataInit() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Auther a = entityManager.find(Auther.class, 1l);
        entityManager.close();
    }

    @Test
    public void refreshEntity() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Auther a = entityManager.find(Auther.class, 1l);
        entityManager.refresh(a);
        entityManager.close();

    }

    @Test
    public void flush() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Auther a = entityManager.find(Auther.class, 1l);
        entityManager.refresh(a);
        entityManager.flush();
        entityManager.close();

    }

    @Test
    public void mergeDetachedEntity() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Auther a = new Auther();
        a.setId(1l);
        Auther b = entityManager.merge(a);
        System.out.println(b.getName());

    }

    @Test
    public void checkEntityPersistentStates() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Auther a = entityManager.find(Auther.class, 1l);

        entityManager.contains(a);
        PersistenceUnitUtil jpaUitl = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
        System.out.println(jpaUitl.isLoaded(a));

        entityManager.close();
    }

    @Test
    public void accessHibernatefromJPA() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        SessionImplementor sessionImplementor = entityManager.unwrap(SessionImplementor.class);
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
