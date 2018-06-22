package com.doctor.spa.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories("com.doctor.spa.repository")
@ComponentScan({"com.doctor.spa"})
/*@PropertySource("classpath:database.properties")*/
@PropertySources({
    @PropertySource("classpath:database.properties"),
    @PropertySource("classpath:application.properties")
})
public class DbConfiguration {
	
	@Autowired
	private Environment env;

 @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
      Environment env) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource);
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.doctor.spa.entity");
    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
    jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
    entityManagerFactoryBean.setJpaProperties(jpaProperties);
    return entityManagerFactoryBean;
  }
	
	

	@Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("spring.primaryDatasource.driverClassName"));
		hikariConfig.setJdbcUrl(env.getProperty("spring.primaryDatasource.jdbc-url"));
		hikariConfig.setUsername(env.getProperty("spring.primaryDatasource.username"));
		hikariConfig.setPassword(env.getProperty("spring.primaryDatasource.password"));
		hikariConfig
				.setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.primaryDatasource.maximum-pool-size")));
		hikariConfig.setPoolName(env.getProperty("spring.primaryDatasource.pool-name"));
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
}
