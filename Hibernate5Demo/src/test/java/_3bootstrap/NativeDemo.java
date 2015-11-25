package _3bootstrap;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import tutorial.Message;

public class NativeDemo {

    @Test
    public void testBootstrapServiceRegistry() {
        BootstrapServiceRegistryBuilder bootstrapRegistryBuilder = new BootstrapServiceRegistryBuilder();
        // add a special ClassLoader
        // bootstrapRegistryBuilder.applyClassLoader(mySpecialClassLoader);
        // manually add an Integrator
        // bootstrapRegistryBuilder.applyIntegrator(mySpecialIntegrator);
        BootstrapServiceRegistry bootstrapRegistry = bootstrapRegistryBuilder.build();
    }

    @Test
    public void testStandardServiceRegistryBuilder() {
        StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();

        BootstrapServiceRegistryBuilder bootstrapRegistryBuilder = new BootstrapServiceRegistryBuilder();
        BootstrapServiceRegistry bootstrapRegistry = bootstrapRegistryBuilder.build();
        standardRegistryBuilder = new StandardServiceRegistryBuilder(bootstrapRegistry);
    }

    @Test
    public void testConfigStandardServiceRegistryBuilder() {
        StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();

        // load some properties via resource lookup
        standardRegistryBuilder.loadProperties("org/hibernate/example/MyProperties.properties");

        // configure the registry from a resource lookup for a cfg.xml config
        // file
        standardRegistryBuilder.configure("org/hibernate/example/my.cfg.xml");

        // apply a random setting
        standardRegistryBuilder.applySetting("myProp", "some value");

        // apply a service initiator
        // standardRegistryBuilder.addInitiator(new CustomServiceInitiator());

        // apply a service impl
        // standardRegistryBuilder.addService(SomeCustomService.class, new
        // SomeCustomServiceImpl());

        // and finally build the StandardServiceRegistry
        StandardServiceRegistry standardRegistry = standardRegistryBuilder.build();
    }

    @Test
    public void MetadataSources() {
        StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();
        StandardServiceRegistry standardRegistry = standardRegistryBuilder.build();
        MetadataSources sources = new MetadataSources(standardRegistry)

        // alternatively, we can build the MetadataSources without passing
        // a service registry, in which case it will build a default
        // BootstrapServiceRegistry to use. But the approach shown
        // above is preferred
        // MetadataSources sources = new MetadataSources();

                // add a class using JPA/Hibernate annotations for mapping
                .addAnnotatedClass(Message.class).

                // add the name of a class using JPA/Hibernate annotations for
                // mapping.
                // differs from above in that accessing the Class is deferred
                // which is
                // important if using runtime bytecode-enhancement
                addAnnotatedClassName("org.hibernate.example.Customer").

                // Adds the named hbm.xml resource as a source: which performs
                // the
                // classpath lookup and parses the XML
                addResource("org/hibernate/example/Order.hbm.xml").

                // Adds the named JPA orm.xml resource as a source: which
                // performs the
                // classpath lookup and parses the XML
                addResource("org/hibernate/example/Product.orm.xml");

        // simpleBuild
        sources.buildMetadata();

        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
        // Use the JPA-compliant implicit naming strategy
        metadataBuilder.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE);

        // specify the schema name to use for tables, etc when none is
        // explicitly specified
        metadataBuilder.applyImplicitSchemaName("my_default_schema");

        Metadata metadata = metadataBuilder.build();
    }

    @Test
    public void testSessionFactory() {
        StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();
        StandardServiceRegistry standardRegistry = standardRegistryBuilder.build();
        MetadataSources sources = new MetadataSources(standardRegistry);
        Metadata metadata = sources.buildMetadata();
        // simple create
        SessionFactory sessionFactory = metadata.buildSessionFactory();

        SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
        // Supply an SessionFactory-level Interceptor
        // sessionFactoryBuilder.applyInterceptor(new
        // MySessionFactoryInterceptor());

        // Add a custom observer
        // sessionFactoryBuilder.addSessionFactoryObservers(new
        // MySessionFactoryObserver());

        // Apply a CDI BeanManager (for JPA event listeners)
        // sessionFactoryBuilder.applyBeanManager(getBeanManagerFromSomewhere());
        sessionFactory = sessionFactoryBuilder.build();

    }
}
