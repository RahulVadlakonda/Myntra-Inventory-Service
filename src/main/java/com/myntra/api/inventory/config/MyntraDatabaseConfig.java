package com.myntra.api.inventory.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class MyntraDatabaseConfig {

	private final Environment environment;

	@Bean
	public DataSource getMyntraDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(environment.getRequiredProperty("datasource.url"));
		dataSource.setUsername(environment.getRequiredProperty("datasource.username"));
		dataSource.setPassword(environment.getRequiredProperty("datasource.password"));
		dataSource.setDriverClassName("org.postgresql.Driver");
		return dataSource;
	}

}
