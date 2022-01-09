package ir.maktab.config;

import ir.maktab.model.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @author Mahsa Alikhani m-58
 */
@EnableJpaRepositories(basePackages = "ir.maktab.dao")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@Configuration
public class HibernateConfig {

    @Autowired
    private Environment environment;


    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty(org.hibernate.cfg.Environment.DRIVER, environment.getProperty("hibernate.connection.driver_class"));
        properties.setProperty(org.hibernate.cfg.Environment.URL, environment.getProperty("hibernate.connection.url"));
        properties.setProperty(org.hibernate.cfg.Environment.USER, environment.getProperty("hibernate.connection.username"));
        properties.setProperty(org.hibernate.cfg.Environment.PASS, environment.getProperty("hibernate.connection.password"));
        properties.setProperty(org.hibernate.cfg.Environment.DIALECT, environment.getProperty("hibernate.dialect"));
        properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.setProperty(org.hibernate.cfg.Environment.FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
        return properties;
    }
}
