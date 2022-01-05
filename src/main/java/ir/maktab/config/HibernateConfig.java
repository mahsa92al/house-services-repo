package ir.maktab.config;

import ir.maktab.model.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Properties;

/**
 * @author Mahsa Alikhani m-58
 */
@Configuration
public class HibernateConfig {

    public static SessionFactory sessionFactory;
    @Bean("sessionFactory")
    @Lazy
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

                configuration.setPhysicalNamingStrategy(new SnakeCasePhysicalNamingStrategy());

                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Expert.class);
                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Offer.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Service.class);
                configuration.addAnnotatedClass(SubService.class);

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/home_services?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "5103583");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(settings);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
