package example.test.springbootmultidb.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Profile({"val-db", "valmsgh2"})
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "messagesEntityManagerFactory",
        transactionManagerRef = "messagesTransactionManager",
        basePackages = {"example.test.springbootmultidb.messages.db.repos"}
)
@EnableScheduling
@ConditionalOnProperty(name="scheduler.enabled", matchIfMissing = true)
public class MessagesDBConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource.messages")
    public DataSourceProperties messagesDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "messagesDataSource")
    public DataSource contactsDataSource() {
        DataSourceProperties messagesDataSourceProperties = messagesDataSourceProperties();

        return DataSourceBuilder.create()
                .driverClassName(messagesDataSourceProperties.getDriverClassName())
                .url(messagesDataSourceProperties.getUrl())
                .username(messagesDataSourceProperties.getUsername())
                .password(messagesDataSourceProperties.getPassword())
                .build();
    }

    @Bean(name = "messagesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean messagesEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("messagesDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean messages = builder
                .dataSource(dataSource)
                .packages("example.test.springbootmultidb.messages.db.entities")
                .persistenceUnit("messages")
                .build();
        messages.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        messages.setJpaProperties(hibernateProperties());

        return messages;
    }

    @Bean(name = "messagesTransactionManager")
    public PlatformTransactionManager messagesTransactionManager(
            @Qualifier("messagesEntityManagerFactory") EntityManagerFactory
                    messagesEntityManagerFactory
    ) {
        return new JpaTransactionManager(messagesEntityManagerFactory);
    }

    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        //hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabasePlatform(env.getProperty("datasource.jpa.messages.database-platform"));
        hibernateJpaVendorAdapter.setGenerateDdl(false);

        return hibernateJpaVendorAdapter;
    }


    private Properties hibernateProperties() {

        final Properties props = new Properties();
        props.setProperty(AvailableSettings.GENERATE_STATISTICS, "false");
        if(env.getProperty("datasource.jpa.messages.default-schema") != null) {
            props.setProperty(AvailableSettings.DEFAULT_SCHEMA, env.getProperty("datasource.jpa.messages.default-schema"));
        }
        props.setProperty(AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, env.getProperty("datasource.jpa.messages.hibernate.transaction.coordinator_class"));

        return props;
    }

    @Bean("initMessages")
    @Profile("valmsgh2")
    public DataSourceInitializer messagesDataSourceInitializer()
    {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(contactsDataSource());
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sql/schema-messages.sql"));
        databasePopulator.addScript(new ClassPathResource("sql/data-messages.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(env.getProperty("datasource.messages.initializate", Boolean.class, false));
        return dataSourceInitializer;
    }
}
