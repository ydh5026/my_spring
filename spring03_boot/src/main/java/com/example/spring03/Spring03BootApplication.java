package com.example.spring03;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.example.spring03.model")
public class Spring03BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring03BootApplication.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { 
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean(); 
		bean.setDataSource(dataSource);
		return bean.getObject(); 
	}

	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory factory) { 
		return new SqlSessionTemplate(factory);
	}
}
