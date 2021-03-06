package com.devsmile.repository;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.devsmile.repository", entityManagerFactoryRef = "UserDTOEntityManager", transactionManagerRef = "UserDTOTransactionManager")
public class DataSourceUserConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource UserDTODataSourse() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean UserDTOEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(UserDTODataSourse());

		em.setPackagesToScan("com.devsmile.model");
		em.setPersistenceUnitName("PERSITENCE_UNIT_USER");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();

		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibenate.show-sql", environment.getProperty("spring.jpa.show-sql"));

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();

		return em;
	}

	@Bean
	public PlatformTransactionManager UserDTOTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(UserDTOEntityManager().getObject());
		return transactionManager;
	}
}
