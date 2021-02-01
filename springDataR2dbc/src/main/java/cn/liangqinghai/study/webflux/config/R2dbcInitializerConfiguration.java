package cn.liangqinghai.study.webflux.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@EnableR2dbcRepositories
public class R2dbcInitializerConfiguration {

    @Bean
    public ConnectionFactoryInitializer h2Init(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("/db/schema.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;

    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory,
                                                   ApplicationContext applicationContext) {

        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);
        template.setApplicationContext(applicationContext);
        return template;

    }

}
