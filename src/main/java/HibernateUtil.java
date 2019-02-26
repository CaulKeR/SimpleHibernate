import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/hiber_db?autoReconnect=true&" +
                    "useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" +
                    "serverTimezone=UTC&allowPublicKeyRetrieval=true");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "*****");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.setProperty(Environment.POOL_SIZE, "5");
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.setProperty(Environment.CACHE_PROVIDER_CONFIG, "org.hibernate.cache.NoCacheProvider");
            properties.setProperty(Environment.SHOW_SQL, "false");

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Game.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        return sessionFactory;
    }
}