package com.example.api;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;

import com.example.pd.AutowireHelper;


@Configuration
@EnableJpaRepositories (
        basePackages = "com.example", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
@EnableJpaAuditing
@EnableScheduling
public class ApplicationConfig
{
    private static final Logger log = LoggerFactory.getLogger( ApplicationConfig.class );

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext ctx;

    @Bean
    AutowireHelper getAutowireHelper()
    {
        return AutowireHelper.getInstance();
    }

    private static final String[] HIBERNATE_PROP_NAMES =
    { "hibernate.dialect", "hibernate.format_sql", "hibernate.hbm2ddl.auto", "hibernate.implicit_naming_strategy",
            "hibernate.show_sql" };

    @Bean
    @Primary
    public DataSource dataSource()
    {
        String usr = env.getProperty( "spring.datasource.username" );
        String pwd = env.getProperty( "spring.datasource.password" );

        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName( env.getRequiredProperty( "spring.datasource.driver-class-name" ) );
        ds.setUrl( env.getRequiredProperty( "spring.datasource.url" ) );

        log.info( "url:" + env.getRequiredProperty( "spring.datasource.url" ) + ", driverClassname:"
                + env.getRequiredProperty( "spring.datasource.driver-class-name" ) );

        ds.setUsername( usr );
        ds.setPassword( pwd );
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vend = new HibernateJpaVendorAdapter();
        vend.setGenerateDdl( true );
        String dialect = env.getRequiredProperty( "hibernate.dialect" );
        if ( dialect.contains( "PostgreSQL" ) )
        {
            vend.setDatabase( Database.POSTGRESQL );
        } else if ( dialect.contains( "HSQL" ) )
        {
            vend.setDatabase( Database.HSQL );
        }
        bean.setDataSource( dataSource() );
        bean.setJpaVendorAdapter( vend );
        bean.setPackagesToScan( "com.example" );
        bean.setPersistenceUnitName( "demo" );
        bean.setJpaProperties( getJpaProperties( env, HIBERNATE_PROP_NAMES ) );
        bean.afterPropertiesSet();
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager()
    {
        EntityManagerFactory emf = ctx.getBean( EntityManagerFactory.class );
        return new JpaTransactionManager( emf );
    }

    private static Properties getJpaProperties( Environment env, String... requiredNames )
    {
        Properties props = new Properties();
        props.put( "org.hibernate.envers.global_with_modified_flag", "true" );
        props.put( "org.hibernate.envers.store_data_at_delete", "true" );
        props.put( "org.hibernate.envers.audit_strategy", "org.hibernate.envers.strategy.ValidityAuditStrategy" );
        props.put( "org.hibernate.envers.audit_strategy_validity_store_revend_timestamp", "true" );
        props.put( "org.hibernate.envers.track_entities_changed_in_revision", "true" );
        props.put( "org.hibernate.envers.revision_on_collection_change", "false" );
        for ( String name : requiredNames )
        {
            props.put( name, env.getRequiredProperty( name ) );
        }
        return props;
    }

}
