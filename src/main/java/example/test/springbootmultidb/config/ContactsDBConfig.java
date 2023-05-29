package example.test.springbootmultidb.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "contactsEntityManagerFactory",
        transactionManagerRef = "contactsTransactionManager",
        basePackages = {"example.test.springbootmultidb.contacts.db.repo"}
)
@Profile({"contacth2", "contactpg"})
public class ContactsDBConfig {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource.contacts")
    public DataSourceProperties contactsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "contactsDataSource")
    public DataSource contactsDataSource() {
        DataSourceProperties contactsDataSourceProperties = contactsDataSourceProperties();

        return DataSourceBuilder.create()
                .driverClassName(contactsDataSourceProperties.getDriverClassName())
                .url(contactsDataSourceProperties.getUrl())
                .username(contactsDataSourceProperties.getUsername())
                .password(contactsDataSourceProperties.getPassword())
                .build();
    }

    @Primary
    @Bean(name = "contactsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("contactsDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean contacts = builder
                .dataSource(dataSource)
                .packages("example.test.springbootmultidb.contacts.db.entities")
                .persistenceUnit("contacts")
                .build();
        contacts.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        contacts.setJpaProperties(hibernateProperties());

        return contacts;
    }

    @Primary
    @Bean(name = "contactsTransactionManager")
    public PlatformTransactionManager contactsTransactionManager(
            @Qualifier("contactsEntityManagerFactory") EntityManagerFactory
                    contactsEntityManagerFactory
    ) {
        return new JpaTransactionManager(contactsEntityManagerFactory);
    }

    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        //hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setDatabasePlatform(env.getProperty("datasource.jpa.contacts.database-platform"));
        hibernateJpaVendorAdapter.setGenerateDdl(false);

        return hibernateJpaVendorAdapter;
    }


    private Properties hibernateProperties() {

        final Properties props = new Properties();
        props.setProperty(AvailableSettings.GENERATE_STATISTICS, "false");
        if(env.getProperty("datasource.jpa.contacts.default-schema") != null) {
            props.setProperty(AvailableSettings.DEFAULT_SCHEMA, env.getProperty("datasource.jpa.contacts.default-schema"));
        }
        props.setProperty(AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, env.getProperty("datasource.jpa.contacts.hibernate.transaction.coordinator_class"));

        return props;
    }

    @Bean("initContacts")
    @Profile("contacth2")
    public DataSourceInitializer contactsDataSourceInitializer()
    {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(contactsDataSource());
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sql/schema-contacts.sql"));
        databasePopulator.addScript(new ClassPathResource("sql/data-contacts.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(env.getProperty("datasource.contacts.initializate", Boolean.class, false));
        return dataSourceInitializer;
    }
}
