package ir.maktab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Mahsa Alikhani m-58
 */
@Configuration
@ComponentScan("ir.maktab")
@Import({HibernateConfig.class})
public class SpringConfig {

}
