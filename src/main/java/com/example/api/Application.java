package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableAutoConfiguration ( exclude =
{ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class } )
@EnableSpringConfigured
@ComponentScan ( basePackages =
{ "com.example.api", "com.example.pd", "com.example.si" } )
public class Application
{
    public static void main( final String[] args )
    {
        SpringApplication.run( Application.class, args );
    }
}
