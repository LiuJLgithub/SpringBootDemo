package com.example.demo.ch5.config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 数据库连接池的配置，这里使用的数据库连接池是HirakaCP
 * @author kdgc_ljl
 *
 */
@Configuration
public class DataSourceConfig {
	@Bean(name="dataSource")
	public DataSource dataSource(Environment env){
		HikariDataSource ds=new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		return ds;
	}
	
}
