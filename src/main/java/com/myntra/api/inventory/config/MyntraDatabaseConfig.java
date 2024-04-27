package com.myntra.api.inventory.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
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
		DataSourceBuilder<HikariDataSource> dataSourceBuilder = (DataSourceBuilder<HikariDataSource>) DataSourceBuilder.create();
		dataSourceBuilder.url(environment.getRequiredProperty("datasource.url"));
		dataSourceBuilder.username(environment.getRequiredProperty("datasource.username"));
		dataSourceBuilder.password(environment.getRequiredProperty("datasource.password"));
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		return dataSourceBuilder.build();
	}

}
