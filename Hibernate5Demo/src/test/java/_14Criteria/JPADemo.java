package _14Criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tutorial.Auther;
import tutorial.Book;

public class JPADemo {
    EntityManagerFactory entityManagerFactory;

    @Test
    public void select() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("id"), 1));
        List<Book> list = entityManager.createQuery(criteria).getResultList();
    }

    @Test
    public void selectExrpession() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root.get("id"));
        criteria.where(builder.equal(root.get("id"), 1));
        List<Integer> list = entityManager.createQuery(criteria).getResultList();
    }

    @Test
    public void multipleValues() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Book> root = criteria.from(Book.class);
        Path<Integer> idPath = root.get("id");
        Path<String> titlePath = root.get("title");

        criteria.select(builder.array(idPath, titlePath));
        // or use this
        criteria.multiselect(idPath, titlePath);

        criteria.where(builder.equal(root.get("id"), 1));
        List<Object[]> list = entityManager.createQuery(criteria).getResultList();
    }

    @Test
    public void wrapper() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Wrapper> criteria = builder.createQuery(Wrapper.class);
        Root<Book> root = criteria.from(Book.class);

        criteria.select(builder.construct(Wrapper.class, root.get("id"), root.get("title")));
        criteria.where(builder.equal(root.get("id"), 1));
        List<Wrapper> list = entityManager.createQuery(criteria).getResultList();
    }

    @Test
    public void tuple() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<Book> root = criteria.from(Book.class);
        Path<Integer> idPath = root.get("id");
        Path<String> titlePath = root.get("title");

        criteria.multiselect(idPath, titlePath);
        criteria.where(builder.equal(root.get("id"), 1));

        List<Tuple> list = entityManager.createQuery(criteria).getResultList();
        for (Tuple tuple : list) {
            tuple.get(idPath);
            tuple.get(titlePath);
        }
    }

    @Test
    public void from() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<Book> rootS1 = criteria.from(Book.class);
        Root<Book> rootS2 = criteria.from(Book.class);

    }

    @Test
    public void join() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Auther> bookAuther = root.join("auther");
        // bookAuther.join();

        List<Book> list = entityManager.createQuery(criteria).getResultList();
        System.out.println(list.size());
    }

    @Test
    public void fetches() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Fetch<Book, Auther> bookAuther = root.fetch("auther");
        // bookAuther.fetch();

        List<Book> list = entityManager.createQuery(criteria).getResultList();
        System.out.println(list.size());

    }

    @Test
    public void parameter() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);

        ParameterExpression<String> parame = builder.parameter(String.class);
        criteria.where(builder.equal(root.get("title"), parame));

        List<Book> list = entityManager.createQuery(criteria).setParameter(parame, "123").getResultList();
        System.out.println(list.size());
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
        entityManagerFactory.close();
    }

}

class Wrapper {
    private final Integer id;
    private final String title;

    public Wrapper(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}