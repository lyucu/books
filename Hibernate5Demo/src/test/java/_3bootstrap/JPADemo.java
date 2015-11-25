package _3bootstrap;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import tutorial.Message;

public class JPADemo {
    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my.jpa");
        EntityManager entityManager = emf.createEntityManager();
        List<Message> result = entityManager.createQuery("from Message", Message.class).getResultList();
        for (Message event : result) {
            System.out.println("Event (" + event.getContent() + ") : " + event.getTitle());
        }
    }
}
