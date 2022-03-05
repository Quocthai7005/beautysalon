package com.doctor.spa.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
	    basePackages = "com.doctor.spa.secondaryrepository", 
	    entityManagerFactoryRef = "subEntityManagerFactory", 
	    transactionManagerRef = "subTransactionManager")
@PropertySources({
    @PropertySource("classpath:database.properties"),
    @PropertySource("classpath:application.properties")
})
@Profile("production")
public class SubDBConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean subEntityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.doctor.spa.subentity");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager subTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public DataSource subDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("spring.secondaryDatasource.driverClassName"));
		hikariConfig.setJdbcUrl(env.getProperty("spring.secondaryDatasource.jdbc-url"));
		hikariConfig.setUsername(env.getProperty("spring.secondaryDatasource.username"));
		hikariConfig.setPassword(env.getProperty("spring.secondaryDatasource.password"));
		hikariConfig
				.setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.secondaryDatasource.maximum-pool-size")));
		hikariConfig.setPoolName(env.getProperty("spring.secondaryDatasource.pool-name"));
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
}
